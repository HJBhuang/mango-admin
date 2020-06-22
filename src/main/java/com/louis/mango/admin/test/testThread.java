package com.louis.mango.admin.test;

/**
 * @author huangjiabao
 * @data 2020/6/19/0019
 * @time 10:35:26
 */
public class testThread {
    public static void main(String[] args) {
         long startTime = System.currentTimeMillis();
    /*    new Thread1().start();
        new Thread2().start();
        new Thread3().start();
      //  new Thread4().start();
        new Thread5().start();*/

       /*new Thread6().start();
        new Thread8().start();
        new Thread9().start();
        new Thread7().start();*/

        new Thread10().start();
         long endTime = System.currentTimeMillis();
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
