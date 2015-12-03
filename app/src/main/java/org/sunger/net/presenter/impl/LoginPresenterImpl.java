package org.sunger.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.sunger.net.app.App;
import org.sunger.net.entity.OauthUserEntity;
import org.sunger.net.model.LoginModel;
import org.sunger.net.presenter.LoginPresenter;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.view.LoginView;

/**
 * Created by sunger on 2015/11/17.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;

    private LoginModel model;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void login(String phone, String pwd) {
        view.showLoading();
        model.login(phone, pwd, new ResultCallback<OauthUserEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showLoginFaile(e.getMessage());
            }

            @Override
            public void onResponse(OauthUserEntity response) {
                App.getInstance().setOauth(response);
                view.loginSuccess();
            }
        });
    }
}
