package com.louis.mango.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author huangjiabao
 * @data 2020/5/17/0017
 * @time 09:56:27
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许跨域访问的路径
        registry.addMapping("/**")
                //运行跨域访问的源
                .allowedOrigins("*")
                //运行请求的方法
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                //预检间隔时间
                .maxAge(168000)
                //允许头部设置
                .allowedHeaders("*")
                //是否发送cookie
                .allowCredentials(true);
    }
}
