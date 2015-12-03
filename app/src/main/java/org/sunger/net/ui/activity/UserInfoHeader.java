package org.sunger.net.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.sunger.net.app.AppUtils;
import org.sunger.net.entity.UserEntity;

import cn.bingoogolapple.badgeview.BGABadgeImageView;
import sunger.org.demo.R;

/**
 * Created by sunger on 15/11/22.
 */
public class UserInfoHeader implements View.OnClickListener {
    protected Activity mActivity;
    protected View mLayoutButton;
    protected BGABadgeImageView mImageViewAvatar;
    protected ImageView mImageViewGender;
    protected ImageView mImageViewEdit;
    protected TextView mTextViewScreenName;
    protected TextView mTextViewFollow;
    protected TextView mTextViewChat;
    protected TextView mTextViewDescription;

    public UserInfoHeader(Activity context, View view) {
        this.mActivity = context;
        mLayoutButton = view.findViewById(R.id.layout_button);
        mImageViewAvatar = (BGABadgeImageView) view.findViewById(R.id.imageView_avatar);
        mTextViewScreenName = (TextView) view.findViewById(R.id.textView_nickName);
        mTextViewFollow = (TextView) view.findViewById(R.id.textView_follow);
        mTextViewChat = (TextView) view.findViewById(R.id.textView_chat);
        mTextViewDescription = (TextView) view.findViewById(R.id.textView_description);
        mImageViewGender = (ImageView) view.findViewById(R.id.imageView_gender);
        mImageViewEdit = (ImageView) view.findViewById(R.id.icon_edit);
    }

    public void bindData(UserEntity entity) {
        entity.setVerified(true);
        mLayoutButton.setVisibility(View.GONE);
        mTextViewScreenName.setText(entity.getScreen_name());
        if (entity.getGender().contains("m"))
            mImageViewGender.setImageResource(R.mipmap.ic_male_white_18dp);
        else {
            mImageViewGender.setImageResource(R.mipmap.ic_female_white_18dp);
        }
        if (TextUtils.isEmpty(entity.getConstellation())) {
            mTextViewDescription.setText(R.string.msg_default_description);
        } else {
            mTextViewDescription.setText(entity.getDescription());
        }
        mTextViewDescription.setOnClickListener(this);
        mImageViewEdit.setOnClickListener(this);
        AppUtils.loadBigUserAvata(mActivity,entity, mImageViewAvatar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_description:
            case R.id.icon_edit:
                mActivity.startActivity(new Intent(mActivity, UserInfoEditActivity.class));
                break;
        }

    }
}
