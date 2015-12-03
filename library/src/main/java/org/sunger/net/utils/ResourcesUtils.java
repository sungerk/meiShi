package org.sunger.net.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ResourcesUtils {

    public static int getColor(View view, int resId) {
        return view.getResources().getColor(resId);
    }
    public static int getColor(Context context, int resId) {
        return context.getResources().getColor(resId);
    }

}
