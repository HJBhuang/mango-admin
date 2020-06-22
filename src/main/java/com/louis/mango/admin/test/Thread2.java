package com.louis.mango.admin.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author huangjiabao
 * @data 2020/6/19/0019
 * @time 10:23:48
 */
public class Thread2 extends Thread {
    @Override
    public void run() {
        try {
             Connection conn = ConnectionMysql.getConn();
            String sql="INSERT INTO city(id,deptname) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            for(int i=303001;i<350000;i++){
                ps.setInt(1,i);
                ps.setString(2,"HJB"+i);
                ps.executeUpdate();
                System.out.println("插入第"+i+"条记录！");
            }
            System.out.println("Thread2操作完成！");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.run();
    }
}
