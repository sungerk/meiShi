package org.sunger.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.sunger.net.app.App;
import org.sunger.net.entity.UserEntity;
import org.sunger.net.model.EditUserInfoModel;
import org.sunger.net.presenter.EditUserInfoPresenter;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.view.EditUserInfoView;

/**
 * Created by sunger on 2015/11/28.
 */
public class EditUserInfoPresenterImpl implements EditUserInfoPresenter {
    private EditUserInfoModel model;
    private EditUserInfoView view;

    public EditUserInfoPresenterImpl(EditUserInfoView view) {
        this.view = view;
        model = new EditUserInfoModel();
    }

    @Override
    public void update(String screen_name, String gender, String description, String filePath) {
        model.update(screen_name, gender, description, filePath, new ResultCallback<UserEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError(e.getMessage());
            }

            @Override
            public void onResponse(UserEntity response) {
                App.getInstance().getOauthUserEntity().setUser(response);
                view.updateSuccess();
            }
        });
    }
}
