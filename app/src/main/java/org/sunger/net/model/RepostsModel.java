package org.sunger.net.model;

import org.sunger.net.api.ApiClient;
import org.sunger.net.api.ParamsMap;
import org.sunger.net.app.AppConstants;
import org.sunger.net.entity.MediaEntity;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by sunger on 2015/12/2.
 */
public class RepostsModel {
    public OkHttpRequest getReposts(int uid, int page, ResultCallback<List<MediaEntity>> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.UID_KEY, uid);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        return ApiClient.create(AppConstants.RequestPath.USER_REPOSTS, paramsMap).get(callback);
    }
}
