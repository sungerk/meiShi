package org.sunger.net.model;

import org.sunger.net.api.ApiClient;
import org.sunger.net.api.ParamsMap;
import org.sunger.net.app.AppConstants;
import org.sunger.net.entity.OauthUserEntity;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

/**
 * Created by sunger on 2015/11/17.
 */
public class LoginModel {
    /**
     * 用户登陆
     *
     * @param phone    手机号码
     * @param password 密码
     * @param callback 回调函数
     * @return
     */
    public OkHttpRequest login(String phone, String password, ResultCallback<OauthUserEntity> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.PHONE_KEY, phone);
        paramsMap.put(AppConstants.ParamKey.PASSWORD_KEY, password);
        paramsMap.put(AppConstants.ParamKey.GRANT_TYPE_KEY, AppConstants.ParamDefaultValue.GRANT_TYPE);
        return ApiClient.create(AppConstants.RequestPath.OAUTH, paramsMap).tag("").post(callback);
    }
}
