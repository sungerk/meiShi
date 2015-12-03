package org.sunger.net.presenter.impl;

import android.os.Handler;

import com.google.gson.JsonElement;
import com.squareup.okhttp.Request;

import org.sunger.net.entity.CommentEntity;
import org.sunger.net.entity.MediaEntity;
import org.sunger.net.model.PlayVideoModel;
import org.sunger.net.presenter.PlayVideoPresenter;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.utils.TimeUtils;
import org.sunger.net.view.PlayVideoView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/10.
 */
public class PlayVideoPresenterImpl implements PlayVideoPresenter {
    private static final int DEF_DELAY = (int) (1 * 1000);
    private PlayVideoModel model;
    private PlayVideoView view;

    public PlayVideoPresenterImpl(PlayVideoView view) {
        this.view = view;
        model = new PlayVideoModel();
    }

    @Override
    public void getMedia(int id) {
        model.getMedias(id, new ResultCallback<MediaEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showLoadMediaError();

            }

            @Override
            public void onResponse(MediaEntity response) {
                view.showMediaData(response);
            }
        });

    }

    private void getComments(int id, final int page) {
        final long current_time = TimeUtils.getCurrentTime();
        model.getComments(id, page, new ResultCallback<List<CommentEntity>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(List<CommentEntity> response) {
                int delay = 0;
                if (TimeUtils.getCurrentTime() - current_time < DEF_DELAY) {
                    //延时一秒
                    delay = DEF_DELAY;
                }
                updateView(response, delay, page);
            }
        });

    }

    private void updateView(final List<CommentEntity> commentEntityList, int delay, final int page) {
        //定时器延时刷新接界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page == 1) {
                    view.refreshComment(commentEntityList);
                } else {
                    view.showMoreComments(commentEntityList);
                }
            }
        }, delay);
    }

    @Override
    public void refresh(int id) {
        getComments(id, 1);
    }

    @Override
    public void loadMore(int id, int page) {
        getComments(id, page);
    }

    @Override
    public void createLikeVideo(int id) {
        model.createVideoLike(id, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(JsonElement response) {

            }
        });
    }

    @Override
    public void destoryLikeVideo(int id) {
        model.destoryVideoLike(id, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(JsonElement response) {

            }
        });
    }

    @Override
    public void createLikeComment(int id) {
        model.createCommentLike(id, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(JsonElement response) {

            }
        });
    }

    @Override
    public void destoryLikeComment(int id) {
        model.destroyCommentLike(id, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(JsonElement response) {

            }
        });
    }
}
