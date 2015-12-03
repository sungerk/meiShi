package org.sunger.net.utils;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/10/28.
 */
public class TimeUtils {
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }


    public static String getTimestamp() {
        return new Timestamp(getCurrentTime()).toString();
    }
}
