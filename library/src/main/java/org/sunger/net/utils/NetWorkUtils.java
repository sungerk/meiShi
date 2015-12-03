package org.sunger.net.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Created by Administrator on 2015/10/27.
 */
public class NetWorkUtils {
    /**
     * 获取当前网络类型
     *
     * @param context
     * @return -1 没有网络
     * 0 移动网络;
     * 1 wifi;
     * 2 其他；
     * @throws Exception
     */
    public static int getNetWorkType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null)
            return -1;
        if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
            return 1;
        } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
            return 0;
        } else {
            return 2;
        }
    }

    public static boolean isMobileType(Context context) {
        return 0 == getNetWorkType(context);
    }

    public static boolean isConnected(Context context) {
        return -1 != getNetWorkType(context);
    }

    public static void openWifi(Activity context) {
         context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }
}
