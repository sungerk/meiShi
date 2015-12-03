package org.sunger.net.model;

import org.sunger.net.api.ApiClient;
import org.sunger.net.app.AppConstants;
import org.sunger.net.entity.CategoryEntity;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

public class CategoryModel {
    /**
     * 获取首页分类列表
     *
     * @param callback
     * @return
     */
    public OkHttpRequest getCategory(ResultCallback<List<CategoryEntity>> callback) {
        return ApiClient.create(AppConstants.RequestPath.CATEGOTY).tag("").get(callback);
    }
}
