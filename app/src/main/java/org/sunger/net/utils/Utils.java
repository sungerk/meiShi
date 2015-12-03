package org.sunger.net.utils;

import android.content.Context;
import android.content.res.TypedArray;

import org.sunger.net.R;


/**
 * Created by Administrator on 2015/11/10.
 */
public class Utils {
    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }


}
