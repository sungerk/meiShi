package org.sunger.net.ui.activity;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.sunger.net.app.AppUtils;
import org.sunger.net.entity.MediaEntity;
import org.sunger.net.ui.widget.VideoControllerView;
import org.sunger.net.utils.DateUtils;
import org.sunger.net.widget.TextImageView;

import cn.bingoogolapple.badgeview.BGABadgeImageView;
import sunger.org.demo.R;

/**
 * Created by sunger on 2015/12/1.
 */
public class VideoPlayHeader {

    private BGABadgeImageView mBgaBadgeImageView;
    private TextView mTextViewScreenName;
    private TextView mTextViewCreatedAt;
    private TextImageView mTextViewPlaysCount;
    private VideoControllerView mVideoControllerView;
    private TextView mTextViewCaption;
    private ImageView mImageViewCommunicationMessage;
    private TextView mTextViewCommunicationMessage;
    private ImageView mImageViewRepeat;
    private ImageView mImageViewThumbUp;
    private TextView mTextViewThumbUp;
    private TextView mTextViewRepeat;
    private Activity mActivity;

    public VideoPlayHeader(Activity activity, View view) {
        this.mActivity = activity;
        mBgaBadgeImageView = (BGABadgeImageView) view.findViewById(R.id.imageView_avatar);
        mTextViewScreenName = (TextView) view.findViewById(R.id.textView_screen_name);
        mTextViewCreatedAt = (TextView) view.findViewById(R.id.textView_created_at);
        mTextViewPlaysCount = (TextImageView) view.findViewById(R.id.textView_plays_count);
        mVideoControllerView = (VideoControllerView) view.findViewById(R.id.videoControllerView);
        mTextViewCaption = (TextView) view.findViewById(R.id.textView_caption);
        mImageViewCommunicationMessage = (ImageView) view.findViewById(R.id.imageView_communication_message);
        mTextViewCommunicationMessage = (TextView) view.findViewById(R.id.textView_communication_message);
        mImageViewRepeat = (ImageView) view.findViewById(R.id.imageView_repeat);
        mImageViewThumbUp = (ImageView) view.findViewById(R.id.imageView_thumb_up);
        mTextViewThumbUp = (TextView) view.findViewById(R.id.textView_thumb_up);
        mTextViewRepeat = (TextView) view.findViewById(R.id.textView_repeat);
    }

    public void bindData(final MediaEntity mediaEntity) {
        AppUtils.loadSmallUserAvata(mActivity, mediaEntity.getUser(), mBgaBadgeImageView);
        mTextViewScreenName.setText(mediaEntity.getUser().getScreen_name());
        mTextViewCreatedAt.setText(DateUtils.getDifference(mediaEntity.getCreated_at()));
        mTextViewPlaysCount.setTextImageStart(15, R.mipmap.ic_visibility_white_24dp, " " + mediaEntity.getPlays_count());
        mTextViewCaption.setText(mediaEntity.getCaption());
        mTextViewCommunicationMessage.setText(mediaEntity.getComments_count() + "");
        mTextViewThumbUp.setText(mediaEntity.getLikes_count() + "");
        mTextViewRepeat.setText(mediaEntity.getReposts_count() + "");
        mVideoControllerView.setVideoPath(mediaEntity.getVideo());
        mVideoControllerView.start();
        mImageViewThumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.thumb_up_scale);
                if (mediaEntity.getLiked()) {
                    mediaEntity.setLiked(false);
                    mediaEntity.setLikes_count(mediaEntity.getLikes_count() - 1);
                    mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_white_18dp);
                    mImageViewThumbUp.startAnimation(animation);
                } else {
                    mediaEntity.setLiked(true);
                    mediaEntity.setLikes_count(mediaEntity.getLikes_count() + 1);
                    mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_blue_18dp);
                    mImageViewThumbUp.startAnimation(animation);
                }
            }
        });
        if (mediaEntity.getLiked()) {
            mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_blue_18dp);
        } else {
            mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_white_18dp);
        }
    }
    public VideoControllerView getVideoControllerView() {
        return mVideoControllerView;
    }






}
