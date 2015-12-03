package org.sunger.net.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.sunger.net.R;
import org.sunger.net.app.App;

/**
 * Created by sunger on 2015/11/16.
 */
public class UiHelper {
    public static Dialog createListDialog(Context context, String[] data, DialogInterface.OnClickListener listener) {
        Dialog alertDialog = new AlertDialog.Builder(context).setItems(data, listener).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return alertDialog;
    }

    /**
     * 创建一个简单的PopupWindow只显示一行文字
     *
     * @return
     */
    public static PopupWindow createSimplePopupWindow(String text, int backgroundColorId) {
        View popView = LayoutInflater.from(App.getInstance()).inflate(R.layout.view_popwindow_text, null);
        TextView textView = (TextView) popView.findViewById(R.id.textView);
        int color = ResourcesUtils.getColor(popView, backgroundColorId);
        textView.setBackgroundColor(color);
        textView.setText(text);
        PopupWindow popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        return popupWindow;
    }

}
