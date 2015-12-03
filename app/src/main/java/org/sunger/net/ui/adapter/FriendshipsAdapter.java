package org.sunger.net.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.sunger.net.app.AppUtils;
import org.sunger.net.entity.SimpleUserEntity;
import org.sunger.net.ui.widget.refresh.BaseLoadMoreRecyclerAdapter;

import cn.bingoogolapple.badgeview.BGABadgeImageView;
import sunger.org.demo.R;

/**
 * Created by sunger on 2015/11/30.
 */
public class FriendshipsAdapter extends BaseLoadMoreRecyclerAdapter<SimpleUserEntity, FriendshipsAdapter.ViewHolder> {
    private Activity activity;

    public FriendshipsAdapter(Activity activity) {
        this.activity = activity;
    }


    @Override
    public ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_friendships_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        SimpleUserEntity entity = getItem(position);
        AppUtils.loadSmallUserAvata(activity, entity, holder.mImageViewAvatar);
        holder.mTextViewScreenName.setText(entity.getScreen_name());
        if (entity.getGender().contains("m")) {
            holder.mImageViewGender.setImageResource(R.mipmap.ic_male_blue_18dp);
        } else {
            holder.mImageViewGender.setImageResource(R.mipmap.ic_female_red_18dp);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final BGABadgeImageView mImageViewAvatar;
        private final TextView mTextViewScreenName;
        private final ImageView mImageViewGender;

        public ViewHolder(View view) {
            super(view);
            mImageViewAvatar = (BGABadgeImageView) view.findViewById(R.id.imageView_avatar);
            mTextViewScreenName = (TextView) view.findViewById(R.id.textView_screen_name);
            mImageViewGender = (ImageView) view.findViewById(R.id.imageView_gender);
        }
    }
}
