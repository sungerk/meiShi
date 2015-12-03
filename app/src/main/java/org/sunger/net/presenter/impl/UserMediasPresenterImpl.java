package org.sunger.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.sunger.net.entity.MediaEntity;
import org.sunger.net.model.UserMediasModel;
import org.sunger.net.presenter.UserMediasPresenter;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.view.MediasView;

import java.util.List;

/**
 * Created by sunger on 2015/10/27.
 */
public class UserMediasPresenterImpl implements UserMediasPresenter {

    private MediasView view;
    private UserMediasModel model;

    public UserMediasPresenterImpl(MediasView view) {
        this.view = view;
        this.model = new UserMediasModel();
    }


    @Override
    public void getMedias(int uid, int page) {
        model.getMedias(uid, page, new ResultCallback<List<MediaEntity>>() {
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
