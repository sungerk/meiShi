package org.sunger.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.sunger.net.entity.FollowEntity;
import org.sunger.net.model.FollowersModel;
import org.sunger.net.presenter.FollowersPresenter;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.view.FollowersView;

import java.util.List;

/**
 * Created by sunger on 2015/11/30.
 */
public class FollowersPresenterImpl implements FollowersPresenter {
    private FollowersModel model;
    private FollowersView view;

    public FollowersPresenterImpl(FollowersView view) {
        this.view = view;
        model = new FollowersModel();
    }

    @Override
    public void getFollowers(String uid, int page) {
        model.getFollowers(uid, page, new ResultCallback<List<FollowEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<FollowEntity> response) {
                view.showFollows(response);
            }
        });


    }
}
