package org.sunger.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.sunger.net.entity.MediaEntity;
import org.sunger.net.model.RepostsModel;
import org.sunger.net.presenter.RepostsMediasPresenter;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.view.MediasView;

import java.util.List;

/**
 * Created by sunger on 2015/12/2.
 */
public class RepostsMediasPresenterImpl implements RepostsMediasPresenter{

    private RepostsModel model;
    private MediasView view;

    public RepostsMediasPresenterImpl(MediasView view) {
        this.view = view;
        this.model=new RepostsModel();
    }

    @Override
    public void getRepostsMedias(int uid, int page) {
        model.getReposts(uid, page, new ResultCallback<List<MediaEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<MediaEntity> response) {
                view.showVideo(response);
            }
        });
    }
}
