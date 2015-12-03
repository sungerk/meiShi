package org.sunger.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.sunger.net.entity.SimpleUserEntity;
import org.sunger.net.model.FriendshipsModel;
import org.sunger.net.presenter.FriendshipsPresenter;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.view.FriendshipsView;

import java.util.List;

/**
 * Created by sunger on 2015/11/30.
 */
public class FriendshipsPresenterImpl implements FriendshipsPresenter {
    private FriendshipsModel model;
    private FriendshipsView view;

    public FriendshipsPresenterImpl(FriendshipsView view) {
        this.view = view;
        model = new FriendshipsModel();
    }

    @Override
    public void getFriends(String uid, int page) {
        model.getFriends(uid, page, new ResultCallback<List<SimpleUserEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<SimpleUserEntity> response) {
                view.showFriends(response);
            }
        });
    }
}
