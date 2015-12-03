package org.sunger.net.model;

import org.sunger.net.api.ApiClient;
import org.sunger.net.api.ParamsMap;
import org.sunger.net.app.AppConstants;
import org.sunger.net.entity.SimpleUserEntity;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by sunger on 2015/11/30.
 */
public class FriendshipsModel{
    public OkHttpRequest getFriends(String uid, int page, ResultCallback<List<SimpleUserEntity>> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.UID_KEY, uid);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        return ApiClient.create(AppConstants.RequestPath.FRIENDSHIPS, paramsMap).tag("").get(callback);
    }
}
