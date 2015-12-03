package org.sunger.net.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sunger.net.R;

public class SolidToast {

    public static final int DURATION_LONG = 3000;
    public static final int DURATION_SHORT = 1200;
    public static final int GRAVITY_DEFAULT = -1;

    private ViewGroup mParent;
    private View mToastView;
    private Activity mActivity;
    private LayoutParams mLayoutParams;

    private TranslateAnimation mUpAnim;
    private AlphaAnimation mFadeInAnim;
    private AlphaAnimation mFadeOutAnim;

    private int mDuration;
    private int mGravity;
    private boolean mIsShowing;
    private boolean mIsReused;
    private int mBackgroundColor;
    private CharSequence mMsg;

    public SolidToast(Context context) {
        mUpAnim = (TranslateAnimation) AnimationUtils.loadAnimation(context,
                R.anim.slide_up_in);
        mFadeInAnim = (AlphaAnimation) AnimationUtils.loadAnimation(context,
                R.anim.fade_in);
        mFadeOutAnim = (AlphaAnimation) AnimationUtils.loadAnimation(context,
                R.anim.fade_out);
        mUpAnim.setDuration(300);
        mFadeInAnim.setDuration(200);
        mFadeOutAnim.setDuration(500);
    }

    /**
     * Return a SolidToast with custom text,default root view and default
     * duration.
     *
     * @param activity current activity
     * @param text     the text you want to display
     * @return
     */
    public static SolidToast make(Activity activity, CharSequence text) {
        return make(activity, text, null, DURATION_SHORT, GRAVITY_DEFAULT);
    }

    public static SolidToast make(Activity activity, int resId) {
        return make(activity, activity.getString(resId));
    }

    /**
     * Return a SolidToast with custom text,custom gravity, default root view
     * and default duration.
     *
     * @param activity current activity
     * @param text     the text you want to display
     * @param gravity  display gravity
     * @return
     */
    public static SolidToast make(Activity activity, CharSequence text,
                                  int gravity) {
        return make(activity, text, null, DURATION_SHORT, gravity);
    }

    public static SolidToast make(Activity activity, int resId, int gravity) {
        return make(activity, activity.getString(resId), gravity);
    }

    /**
     * Return a SolidToast with custom text,default root view and display
     * duration.
     *
     * @param activity current activity
     * @param text     the text you want to display
     * @param duration display duration, in ms.
     * @return SolidToast instance.
     */
    public static SolidToast make(Activity activity, CharSequence text,
                                  int duration, int gravity) {
        return make(activity, text, null, duration, gravity);
    }

    public static SolidToast make(Activity activity, int resId, int duration,
                                  int gravity) {
        return make(activity, activity.getString(resId), duration, gravity);
    }

    public static SolidToast make(Activity activity, int resId,
                                  ViewGroup parent, int duration, int gravity) {
        return make(activity, activity.getString(resId), parent, duration,
                gravity);
    }

    /**
     * Return a SolidToast with custom text,parent view and display duration.
     *
     * @param activity current activity
     * @param text     the text you want to display
     * @param parent   parent view of toast display on
     * @param duration display duration, in ms.
     * @return SolidToast instance.
     */
    public static SolidToast make(Activity activity, CharSequence text,
                                  ViewGroup parent, int duration, int gravity) {
        boolean isReused = true;
        View view = activity.findViewById(R.id.toast_background);
        if (view == null) {
            view = View.inflate(activity, R.layout.view_solid_toast, null);
            isReused = false;
        }
        LinearLayout background = (LinearLayout) view
                .findViewById(R.id.toast_background);
        TextView msg = (TextView) view.findViewById(R.id.toast_msg);
        background.setBackgroundColor(Color.BLUE);
        msg.setText(text);
        SolidToast toast = new SolidToast(activity);
        toast.setViewIsReused(isReused);
        toast.setMsg(text);
        toast.setParent(parent);
        toast.setActivity(activity);
        toast.setDuration(duration);
        toast.setGravity(gravity);
        toast.setToastView(view);
        return toast;
    }

    public static boolean isToastShowing(Activity activity) {
        View view = getToastView(activity);
        return view != null && view.getVisibility() == View.VISIBLE;
    }

    public static View getToastView(Activity activity) {
        return activity.findViewById(R.id.toast_background);
    }

    public static void hideToastView(Activity activity) {
        View view = getToastView(activity);
        if (view != null && view.getVisibility() == View.VISIBLE) {
            AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils
                    .loadAnimation(activity, R.anim.fade_out);
            alphaAnimation.setDuration(300);
            view.startAnimation(alphaAnimation);
            view.setVisibility(View.GONE);
        }
    }

    public View getToastView() {
        return mToastView;
    }

    public void setToastView(View view) {
        mToastView = view;
        mToastView.setLayoutParams(getLayoutParams());
    }

    public void setViewIsReused(boolean isReused) {
        mIsReused = isReused;
    }

    public ViewGroup getParent() {
        return mParent;
    }

    public void setParent(ViewGroup parent) {
        this.mParent = parent;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public LayoutParams getLayoutParams() {
        if (mLayoutParams == null) {
            mLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT, getGravity());
        }
        return mLayoutParams;
    }

    public void setLayoutParams(LayoutParams mLayoutParams) {
        this.mLayoutParams = mLayoutParams;
    }

    public int getGravity() {
        if (mGravity == GRAVITY_DEFAULT)
            mGravity = Gravity.BOTTOM;
        return mGravity;
    }

    public void setGravity(int mGravity) {
        this.mGravity = mGravity;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    public CharSequence getMsg() {
        return mMsg;
    }

    public void setMsg(CharSequence msg) {
        mMsg = msg;
    }

    public boolean isShowing() {
        return mIsShowing;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public SolidToast setBackgroundColorId(int colorId) {
        int color = getToastView().getResources().getColor(colorId);
        mBackgroundColor = color;
        getToastView().setBackgroundColor(color);
        return this;
    }

    /**
     * display prepared SolidToast
     */
    public void show() {
        if (TextUtils.isEmpty(mMsg))
            return;

        mIsShowing = true;
        View view = getToastView();
        if (mIsReused) {

            TextView textView = (TextView) getActivity().findViewById(
                    R.id.toast_msg);
            if (textView != null) {
                textView.setText(mMsg);
            }
        } else {
            if (view.getParent() == null) {
                if (getParent() != null) {
                    getParent().addView(view, getLayoutParams());
                } else {
                    getActivity().addContentView(view, getLayoutParams());
                }
            }
        }

        view.clearAnimation();
        if (getGravity() == Gravity.BOTTOM)
            view.startAnimation(mUpAnim);
        else if (getGravity() == Gravity.TOP)
            view.startAnimation(mFadeInAnim);

        if (view.getVisibility() != View.VISIBLE)
            view.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                hide();
            }
        }, getDuration());
    }

    /**
     * display SolidToast with specified margin based on gravity
     *
     * @param margin
     */
    public void showWithMargin(int margin) {
        LayoutParams params = getLayoutParams();
        switch (getGravity()) {
            case Gravity.BOTTOM:
                params.bottomMargin = margin;
                break;
            case Gravity.TOP:
                params.topMargin = margin;
                break;
            case Gravity.LEFT:
                params.leftMargin = margin;
                break;
            case Gravity.RIGHT:
                params.rightMargin = margin;
                break;
        }
        show();
    }

    public void hide() {
        View toastView = getToastView();
        if (mIsShowing && toastView.getVisibility() == View.VISIBLE) {
            toastView.startAnimation(mFadeOutAnim);
            toastView.setVisibility(View.GONE);
            mIsShowing = false;
        }
    }

}
