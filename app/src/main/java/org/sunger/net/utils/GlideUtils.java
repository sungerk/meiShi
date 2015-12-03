package org.sunger.net.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.sunger.net.app.App;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by sunger on 2015/10/29.
 */
public class GlideUtils {
    /**
     * 加载圆角图片
     *
     * @param
     * @param
     */
    public static void loadCircleImage(BitmapTypeRequest bitmapTypeRequest, final Object object) {
        bitmapTypeRequest.into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable circleDrawable = DrawableUtils.roundedBitmap(resource);
                if (object instanceof ImageView) {
                    ((ImageView) object).setImageDrawable(circleDrawable);
                    return;
                }
                if (object instanceof Preference) {
                    ((Preference) object).setIcon(circleDrawable);
                }

            }

        });
    }


    /**
     * 加载带V效果
     *
     * @param imageView
     */
    public static void loadBadgeImage(int vIcon, BitmapTypeRequest bitmapTypeRequest, final BGABadgeImageView imageView) {
        final Bitmap avatorBadgeBitmap = BitmapFactory.decodeResource(App.getInstance().getResources(), vIcon);
        bitmapTypeRequest.into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable circleDrawable = DrawableUtils.roundedBitmap(resource);
                imageView.setImageDrawable(circleDrawable);
                imageView.showDrawableBadge(avatorBadgeBitmap);
            }

        });
    }
}
