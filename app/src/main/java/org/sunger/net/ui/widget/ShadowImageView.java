package org.sunger.net.ui.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by sunger on 15/11/21.
 */
public class ShadowImageView extends SimpleDraweeView {

    public ShadowImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
     }

    public ShadowImageView(Context context) {
        super(context);
     }

    public ShadowImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
     }

    public ShadowImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
     }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                setColorFilter(Color.parseColor("#77000000"));
//                break;
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_OUTSIDE:
//                setColorFilter(null);
//                break;
//        }
//
//
//        return super.onTouchEvent(event);
//    }


    public  void setNetWorkPath(String path){
        setImageURI(Uri.parse(path));
    }


}
