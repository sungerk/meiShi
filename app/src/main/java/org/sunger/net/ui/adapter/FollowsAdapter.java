package org.sunger.net.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.sunger.net.app.AppUtils;
import org.sunger.net.entity.FollowEntity;
import org.sunger.net.ui.widget.refresh.BaseLoadMoreRecyclerAdapter;
import org.sunger.net.utils.DateUtils;

import cn.bingoogolapple.badgeview.BGABadgeImageView;
import sunger.org.demo.R;

/**
 * Created by sunger on 2015/11/30.
 */
public class FollowsAdapter extends BaseLoadMoreRecyclerAdapter<FollowEntity, FollowsAdapter.ViewHolder> {
    private Activity mActivity;

    public FollowsAdapter(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_follows_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        FollowEntity entity = getItem(position);
        AppUtils.loadSmallUserAvata(mActivity, entity, holder.mImageViewAvatar);
        holder.mTextViewScreenName.setText(entity.getScreen_name());
        holder.mTextViewCaption.setText(entity.getCaption());
        holder.mTextViewFollowedByAt.setText(DateUtils.getDifference(entity.getFollowed_by_at() + ""));
        if (entity.getGender().contains("m")) {
            holder.mImageViewGender.setImageResource(R.mipmap.ic_male_blue_18dp);
        } else {
            holder.mImageViewGender.setImageResource(R.mipmap.ic_female_red_18dp);
        }
        if (entity.isFollowing()) {
            holder.mButtonFollow.setVisibility(View.GONE);
            holder.mImageViewNavigationRight.setVisibility(View.VISIBLE);
        } else {
            holder.mButtonFollow.setVisibility(View.VISIBLE);
            holder.mImageViewNavigationRight.setVisibility(View.GONE);
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final BGABadgeImageView mImageViewAvatar;
        private final TextView mTextViewScreenName;
        private final ImageView mImageViewGender;
        private final TextView mTextViewCaption;
        private final TextView mTextViewFollowedByAt;
        private final Button mButtonFollow;
        private final View mImageViewNavigationRight;

        public ViewHolder(View view) {
            super(view);
            mImageViewAvatar = (BGABadgeImageView) view.findViewById(R.id.imageView_avatar);
            mTextViewScreenName = (TextView) view.findViewById(R.id.textView_screen_name);
            mTextViewCaption = (TextView) view.findViewById(R.id.textView_caption);
            mImageViewGender = (ImageView) view.findViewById(R.id.imageView_gender);
            mTextViewFollowedByAt = (TextView) view.findViewById(R.id.textView_followed_by_at);
            mButtonFollow = (Button) view.findViewById(R.id.button_follow);
            mImageViewNavigationRight = view.findViewById(R.id.imageView_navigation_right);
        }
    }
}
