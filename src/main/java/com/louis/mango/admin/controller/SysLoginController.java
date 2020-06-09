package com.louis.mango.admin.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.louis.common.http.HttpResult;
import com.louis.common.utils.PasswordUtils;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.security.JwtAuthenticatioToken;
import com.louis.mango.admin.service.SysLoginLogService;
import com.louis.mango.admin.service.SysUserService;
import com.louis.mango.admin.util.IPUtils;
import com.louis.mango.admin.util.SecurityUtils;
import com.louis.mango.admin.vo.LoginBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Api("登录控制器")
@RestController
@Slf4j
public class SysLoginController {
    @Autowired
    private Producer producer;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysLoginLogService sysLoginLogService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation("生成验证码")
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        log.debug("生成的验证码是:{}",text);
        //生成图片验证码
        BufferedImage image=producer.createImage(text);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        IOUtils.closeQuietly(out);
    }

    @ApiOperation("登录接口")
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getAccount();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(kaptcha == null){
			return HttpResult.error("验证码已失效");
		}
		if(!captcha.equals(kaptcha)){
			return HttpResult.error("验证码不正确");
		}
        // 用户信息
        SysUser user = sysUserService.findByName(username);
        // 账号不存在、密码错误
        if (user == null) {
            return HttpResult.error("账号不存在");
        }
        if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
            return HttpResult.error("密码不正确");
        }
        // 账号锁定
        if (user.getStatus() == 0) {
            return HttpResult.error("账号已被锁定,请联系管理员");
        }
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
        // 记录登录日志
        sysLoginLogService.writeLoginLog(username, IPUtils.getIpAddr(request));
        return HttpResult.ok(token);
    }

}
