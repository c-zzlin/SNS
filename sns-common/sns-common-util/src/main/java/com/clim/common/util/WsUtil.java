package com.clim.common.util;

public class WsUtil {
    /**
     * 判断是否主页的websocket
     * @param s
     * @return
     */
    public static boolean is_main(String s){
        return s.equals("main");
    }
    public static boolean is_pm(String s){
        return s.equals("pm");
    }
}
