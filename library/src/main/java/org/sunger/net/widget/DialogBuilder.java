package org.sunger.net.widget;

/**
 * Created by sunger on 15/11/15.
 */
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

/**
 * 没有系统头部的dialog
 * Created by sunger on 15/5/29.
 */
public class DialogBuilder {
    private  Context context;
    private  View dialogView;
    private  int dialogViewId=-1;
    public DialogBuilder(Context context){
        this.context=context;
    }

    public Dialog create(){
        Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogView!=null)
            dialog.setContentView(dialogView);
        else if(dialogViewId!=-1)
            dialog.setContentView(dialogViewId);
        return  dialog;
    }

    public DialogBuilder setView(View v){
        this.dialogView=v;
        return this;
    }
    public DialogBuilder setView(int viewId ){
        this.dialogViewId=viewId;
        return this;
    }
}