package org.sunger.net.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;

import org.sunger.net.entity.MediaEntity;
import org.sunger.net.entity.SimpleUserEntity;
import org.sunger.net.entity.VideoItemEntity;
import org.sunger.net.ui.widget.AvatarView;

import java.util.ArrayList;
import java.util.List;

import sunger.org.demo.R;

/**
 * Created by sunger on 15/11/22.
 */
public class AppUtils {


    public static void loadBigUserAvata(SimpleUserEntity entity, AvatarView imageView) {
        loadImage(R.mipmap.ic_verified_account_24dp, entity, imageView);
    }

    public static void loadSmallUserAvata(SimpleUserEntity entity, AvatarView imageView) {
        loadImage(R.mipmap.ic_verified_account_16dp, entity, imageView);
    }

    private static void loadImage(int vIcon, SimpleUserEntity entity, AvatarView imageView) {
        if (TextUtils.isEmpty(entity.getAvatar()))
            return;
        imageView.setImageURI(Uri.parse(entity.getAvatar()));
        if (entity.getVerified()) {
            Bitmap avatorBadgeBitmap = BitmapFactory.decodeResource(App.getInstance().getResources(), vIcon);
            imageView.showDrawableBadge(avatorBadgeBitmap);
        }
    }


    public static List<MediaEntity> toMediaList(List<VideoItemEntity> dataList) {
        List<MediaEntity> data = new ArrayList<>();
        for (VideoItemEntity entity : dataList) {
            MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.setId(entity.getMedia().getId());
            mediaEntity.setUser(entity.getMedia().getUser());
            mediaEntity.setLikes_count(entity.getMedia().getLikes_count());
            mediaEntity.setCaption(entity.getRecommend_caption());
            mediaEntity.setCover_pic(entity.getRecommend_cover_pic());
            data.add(mediaEntity);
        }
        return data;
    }
}
