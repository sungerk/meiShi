package org.sunger.net.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import org.sunger.net.entity.CommentEntity;
import org.sunger.net.entity.MediaEntity;
import org.sunger.net.presenter.PlayVideoPresenter;
import org.sunger.net.presenter.impl.PlayVideoPresenterImpl;
import org.sunger.net.support.recyclerview.OnRecycleViewScrollListener;
import org.sunger.net.support.recyclerview.TopScrollListener;
import org.sunger.net.ui.adapter.CommentsAdapter;
import org.sunger.net.view.PlayVideoView;

import java.util.List;

import sunger.org.demo.R;

public class VideoPlayActivity extends BaseCompatActivity implements PlayVideoView, SwipeRefreshLayout.OnRefreshListener, CommentsAdapter.OnCommentItemClickListener,AppBarLayout.OnOffsetChangedListener {
    public final static String MEDIAS_ID_KEY = "media_id";
    private RecyclerView mRecyclerView;
    private CommentsAdapter mAdapter;
    private PlayVideoPresenter mPresenter;
    private VideoPlayHeader mVideoPlayHeader;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private int medias_id;
    private int current_comment_page = 1;

    public static Intent createIntent(Context context, int id) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra(MEDIAS_ID_KEY, id);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_video_play);
        setStateBarColor(R.color.colorPrimaryDark);
        medias_id = getIntent().getIntExtra(MEDIAS_ID_KEY, -1);
        mPresenter = new PlayVideoPresenterImpl(this);
        mPresenter.getMedia(medias_id);
        initView();
        mPresenter.refresh(medias_id);
    }

    private void resetCollapsingToolbarLayout(CollapsingToolbarLayout collapsing_toolbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            collapsing_toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
    }

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.tool_bar);
        setUpCommonBackTooblBar(R.id.tool_bar, "视频播放");
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);
        CollapsingToolbarLayout collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ViewCompat.requestApplyInsets(collapsing_toolbar);
        collapsing_toolbar.setTitleEnabled(false);
        collapsing_toolbar.setExpandedTitleColor(Color.TRANSPARENT);
        resetCollapsingToolbarLayout(collapsing_toolbar);
        mVideoPlayHeader = new VideoPlayHeader(this, findViewById(R.id.video_header));
        initRecyclerView();
    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommentsAdapter(this);
        mAdapter.setHasMoreData(true);
        mRecyclerView.addOnScrollListener(new OnRecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                mAdapter.setHasFooter(true);
                mPresenter.loadMore(medias_id, current_comment_page);
                mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnCommentItemClickListener(this);
        mRecyclerView.addOnScrollListener(new TopScrollListener() {
            protected void start() {
                mVideoPlayHeader.getVideoControllerView().start();
            }

            protected void pause() {
                mVideoPlayHeader.getVideoControllerView().pause();
            }

        });
    }


    private void setHeaderView(MediaEntity mediaEntity) {
        mVideoPlayHeader.bindData(mediaEntity);
    }

    @Override
    public void refreshComment(List<CommentEntity> dataList) {
        current_comment_page = 2;
        mAdapter.clear();
        mAdapter.appendToList(dataList);
        if (dataList.isEmpty()) {
            mAdapter.setHasMoreData(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreComments(List<CommentEntity> dataList) {
        if (dataList.isEmpty()) {
            mAdapter.setHasMoreData(false);
            showMsgInBottom(R.string.msg_no_more_data);
        } else {
            current_comment_page++;
            mAdapter.appendToList(dataList);
            mAdapter.notifyDataSetChanged();
            mAdapter.setHasMoreData(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoPlayHeader != null)
            mVideoPlayHeader.getVideoControllerView().release();
    }

    @Override
    public void showMediaData(MediaEntity mediaEntity) {
        setHeaderView(mediaEntity);
    }

    @Override
    public void showLoadMediaError() {

    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(medias_id);
    }


    @Override
    public void onItemClick(CommentEntity commentEntity) {
        //        Dialog dialog = UiHelper.createListDialog(this, data, new CommentOnClickListener(position));
//        dialog.show();
    }

    @Override
    public void onClickAvatar(int uid) {
    }

    @Override
    public void onClickAtFriend(String screen_name) {

    }

    @Override
    public void thumbUp(int id) {
        mPresenter.createLikeComment(id);
    }

    @Override
    public void thumbDown(int id) {
        mPresenter.destoryLikeComment(id);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float alpha = (float) Math.abs(verticalOffset) / (float) appBarLayout.getTotalScrollRange() * 1.0f;
        if(alpha>0.85f){
            toolbar.setAlpha(alpha);
            toolbar.setTitle("视频播放");
        }else{
            toolbar.setTitle(" ");
            toolbar.setAlpha(alpha+0.2f);
        }

       // toolbar.setAlpha(alpha);
    }

//    /**
//     * 点击回复弹出
//     */
//    private class CommentOnClickListener implements DialogInterface.OnClickListener {
//        private int position;
//
//        public CommentOnClickListener(int position) {
//            this.position = position - 1;
//        }
//
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            switch (which) {
//                case 0:
//                    break;
//                case 1:
//                    ClipboardUtils.copy(VideoPlayActivity.this, commentsAdapter.getItem(position).getContent());
//                    dialog.dismiss();
//                    showMsgInBottom("已复制到剪切板");
//                    break;
//                case 2:
//                    startActivity(WebViewActivity.createIntent(VideoPlayActivity.this, "http://www.baidu.com"));
//                    break;
//                case 3:
//                    dialog.dismiss();
//                    break;
//            }
//        }
//
//    }
}
