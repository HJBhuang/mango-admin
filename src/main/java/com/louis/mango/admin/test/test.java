package com.louis.mango.admin.test;

import java.sql.*;

/**
 * @author huangjiabao
 * @data 2020/6/18/0018
 * @time 17:45:10
 */
public class test {
    public static void main(String[] args) {
        //预处理--注入数据
        long startTime = System.currentTimeMillis();
        //加载数据库
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接对象
            String url = "jdbc:mysql://192.168.109.128:8066/TESTDB?characterEncoding=UTF-8&&autoReconnect=true&failOverReadOnly=false&useSSL=false";
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "123456");
                //查询数据
                //select(conn);

                //插入数据
                //insert(conn);

                //批量插入数据
                String sql="INSERT INTO sensor_data(id,create_time) VALUES(?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                String month="";
                long id=0L;
                String time="";
                for(int i=1;i<300000;i++){
                     id = SnowflakeIdWorkerUtils.generateId();
                     month =i%12+"";
                     if(month.equals("0")){
                         month="12";
                     }
                     if(month.length()==1){
                         month="0"+month;
                     }
                     time = "2020-"+month+"-15 15:22:22";
                    System.out.println("time:"+time+"------id:"+id);

                    ps.setLong(1,id);
                    ps.setString(2,time);
                    ps.executeUpdate();
                    time="";
                    month="";
                    System.out.println("插入第"+i+"条记录！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //释放资源

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        String time2 = millisToStringShort(endTime - startTime);
        System.out.println("时间："+time2);
        System.out.println("操作完成");
    }

    private static void insert(Connection conn) throws SQLException {
        String sql="INSERT INTO sys_dept(id,deptname) VALUES(?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,4);
        ps.setString(2,"HJB");
        ps.executeUpdate();
    }

    private static void select(Connection conn) throws SQLException {
        String sql="SELECT * FROM sys_dept LIMIT 1000;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getInt("id")+"----"+rs.getString("deptname"));
        }
    }


    /**
     * 把毫秒数转换成时分秒
     * @param millis
     * @return
     */
    public static String millisToStringShort(long millis) {
        StringBuffer strBuilder = new StringBuffer();
        long temp = millis;
        long hper = 60 * 60 * 1000;
        long mper = 60 * 1000;
        long sper = 1000;
        if (temp / hper > 0) {
            strBuilder.append(temp / hper).append("小时");
        }
        temp = temp % hper;

        if (temp / mper > 0) {
            strBuilder.append(temp / mper).append("分钟");
        }
        temp = temp % mper;
        if (temp / sper > 0) {
            strBuilder.append(temp / sper).append("秒");
        }
        return strBuilder.toString();
    }
}
