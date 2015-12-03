package org.sunger.net.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.gc.materialdesign.widgets.Dialog;

import org.sunger.net.support.okhttp.OkHttpClientManager;
import org.sunger.net.utils.NetWorkUtils;
import org.sunger.net.utils.SystemBarTintManager;
import org.sunger.net.utils.UiHelper;
import org.sunger.net.widget.SolidToast;

import sunger.org.demo.R;


/**
 * Created by sunger on 2015/10/27.
 */
public class BaseCompatActivity extends AppCompatActivity {
    protected Handler taskHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStateBarColor(R.color.colorPrimaryDark);
    }

    protected void setStateBarColor(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(resId);
            tintManager.setStatusBarDarkMode(true, this);
        }
    }


    protected void setUpCommonBackTooblBar(int toolbarId, String title) {
        Toolbar mToolbar = (Toolbar) findViewById(toolbarId);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        toobarAsBackButton(mToolbar);
    }

    protected void setUpCommonBackTooblBar(int toolbarId, int titleId) {
        setUpCommonBackTooblBar(toolbarId, getString(titleId));
    }

    public int getActionBarSize() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return 0;
    }


    public View getRootView() {
        return findViewById(android.R.id.content);
    }

    private void showOpenWifiDialog() {
        final Dialog dialog = new Dialog(this, "美视提醒", "检测到当前网络没有连接是否开启网络？");
        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkUtils.openWifi(BaseCompatActivity.this);
                dialog.dismiss();
            }
        });
        dialog.addCancelButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showExitDialog(String msg) {
        final Dialog dialog = new Dialog(this, "美视提醒", msg);
        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.addCancelButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                exitApp();
            }
        });
        dialog.show();
    }

    /**
     * toolbar点击返回，模拟系统返回
     * 设置toolbar 为箭头按钮
     * app:navigationIcon="?attr/homeAsUpIndicator"
     *
     * @param toolbar
     */
    public void toobarAsBackButton(Toolbar toolbar) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    /**
     * 检测网络
     */
    protected void checkNetWork() {
        if (!NetWorkUtils.isConnected(this)) {
            showOpenWifiDialog();
        }
        if (NetWorkUtils.isMobileType(this)) {
            showExitDialog("当前网络是移动网络，观看视频流量消耗会比较大，实现继续观看？取消将退出应用");
        }
    }

    public void exitApp() {
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    public void showPopWindwow(View parent, String text) {
        final PopupWindow popupWindow = UiHelper.createSimplePopupWindow(text, R.color.colorPrimaryDark);
        popupWindow.showAsDropDown(parent);
        taskHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        }, 1500);
    }

    public void showMsgInBottom(String msg) {
        SolidToast.make(this, msg, Gravity.BOTTOM).setBackgroundColorId(R.color.colorPrimaryDark).show();
    }

    public void showMsgInBottom(int msgSringId) {
        showMsgInBottom(getString(msgSringId));
    }

    protected <T extends View> T findView(int id) {
        return (T) super.findViewById(id);
    }

    public void cancelHttpRequest(String tag) {
        OkHttpClientManager.getInstance().cancelTag(tag);
    }

}
