package com.clim.blog.util;

import java.util.Calendar;

public class DateUtil {
    public static String getDate(){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   

        int year = c.get(Calendar.YEAR);

        int month = c.get(Calendar.MONTH);

        int date = c.get(Calendar.DATE);

        int hour = c.get(Calendar.HOUR_OF_DAY);

        int minute = c.get(Calendar.MINUTE);

        int second = c.get(Calendar.SECOND);
        String image=year + "" + month + "" + date + "" +hour + "" +minute + "" + second;
        return image;
    }
}
