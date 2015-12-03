package org.sunger.net.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by sunger on 15/11/21.
 */
public class ShadowImageView extends ImageView {
    public ShadowImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShadowImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShadowImageView(Context context) {
        super(context);
    }

    @TargetApi(21)
    public ShadowImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setColorFilter(Color.parseColor("#77000000"));
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_OUTSIDE:
                setColorFilter(null);
                break;
        }


        return super.onTouchEvent(event);
    }


}
