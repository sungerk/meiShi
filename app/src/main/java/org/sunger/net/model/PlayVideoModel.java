package org.sunger.net.model;

import com.google.gson.JsonElement;

import org.sunger.net.api.ApiClient;
import org.sunger.net.api.HeaderMap;
import org.sunger.net.api.ParamsMap;
import org.sunger.net.app.AppConstants;
import org.sunger.net.entity.CommentEntity;
import org.sunger.net.entity.MediaEntity;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by sunger on 2015/11/10.
 */
public class PlayVideoModel  {
    /**
     * 获取视频信息
     *
     * @param id
     * @param callback
     * @return
     */
    public OkHttpRequest getMedias(int id, ResultCallback<MediaEntity> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        return ApiClient.create(AppConstants.RequestPath.MEDIAS, paramsMap).tag( "").get(callback);
    }

    /**
     * 获取视频评论
     *
     * @param id
     * @param page
     * @param callback
     * @return
     */

    public OkHttpRequest getComments(int id, int page, ResultCallback<List<CommentEntity>> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.COMMENTS, paramsMap, headerMap).tag("").get(callback);
    }

    /**
     * 视频点赞
     *
     * @param id       视频id
     * @param callback
     * @return
     */

    public OkHttpRequest createVideoLike(int id, ResultCallback<JsonElement> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.LIKES_VIDEO_CREATE, paramsMap, headerMap).tag("").post(callback);
    }

    /**
     * 视频取消赞
     *
     * @param id
     * @param callback
     * @return
     */
    public OkHttpRequest destoryVideoLike(int id, ResultCallback<JsonElement> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.LIKES_VIDEO_DESTORY, paramsMap, headerMap).tag("").post(callback);
    }

    /**
     * 评论点赞
     *
     * @param id
     * @param callback
     * @return
     */

    public OkHttpRequest createCommentLike(int id, ResultCallback<JsonElement> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.CREATE_COMMENTS_LIKE, paramsMap, headerMap).tag("").post(callback);
    }

    /**
     * 取消评论赞
     *
     * @param id
     * @param callback
     * @return
     */
    public OkHttpRequest destroyCommentLike(int id, ResultCallback<JsonElement> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.ID_KEY, id);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.DESTORY_COMMENT_LIKE, paramsMap, headerMap).tag("").post(callback);
    }
}
