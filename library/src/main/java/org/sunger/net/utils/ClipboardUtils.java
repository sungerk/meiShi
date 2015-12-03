package org.sunger.net.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by sunger on 15/11/15.
 */
public class ClipboardUtils {
    public static void copy(Context context,String str) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        String lable = str;
        if (str.length() > 10)
            lable = str.substring(0, 10);
        clipboardManager.setPrimaryClip(ClipData.newPlainText(lable, str));
    }
}
