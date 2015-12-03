package org.sunger.net.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import org.sunger.net.app.App;

/**
 * Created by sunger on 15/11/29.
 */
public class DrawableUtils {


    public static Drawable roundedBitmap(Bitmap bitmap) {
        RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(App.getInstance().getResources(), bitmap);
        circleDrawable.getPaint().setAntiAlias(true);
        circleDrawable.setCircular(true);
        circleDrawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
        return circleDrawable;
    }
}
