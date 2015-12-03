package org.sunger.net.presenter.impl;

import android.os.Handler;

import com.squareup.okhttp.Request;

import org.sunger.net.app.AppUtils;
import org.sunger.net.entity.MediaEntity;
import org.sunger.net.entity.VideoItemEntity;
import org.sunger.net.model.HomeMediasModel;
import org.sunger.net.presenter.HomeMediasPresenter;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.utils.TimeUtils;
import org.sunger.net.view.HomeMediasView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class HomeMediasPresenterImpl implements HomeMediasPresenter {
    //请求一次默认最短时间
    private static final int DEF_DELAY = (int) (1 * 1000);
    private HomeMediasModel model;
    private HomeMediasView view;

    public HomeMediasPresenterImpl(HomeMediasView view) {
        this.view = view;
        model = new HomeMediasModel();
    }

    /**
     * 判断是不是热门视频
     *
     * @param id
     * @param type
     * @return
     */
    private boolean isHot(int id, int type) {
        return id == 1 || type == 1;
    }

    private void getVideo(int id, int type, int page, int count, ResultCallback<List<VideoItemEntity>> callback) {
        if (isHot(id, type)) {
            model.getHotVideoList(page, count, callback);
        } else {
            model.getVideoList(id, type, page, count, callback);
        }
    }

    private void loadData(int id, int type, final int page, int count) {
        //这里设一个变量标记时间，避免UI刷新过快
        final long current_time = TimeUtils.getCurrentTime();
        getVideo(id, type, page, count, new ResultCallback<List<VideoItemEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<VideoItemEntity> response) {
                for (Iterator<VideoItemEntity> it = response.iterator(); it.hasNext(); ) {
                    VideoItemEntity entity = it.next();
                    if (entity.getMedia() == null) {
                        it.remove();
                    }
                }
                int delay = 0;
                if (TimeUtils.getCurrentTime() - current_time < DEF_DELAY) {
                    //延时一秒
                    delay = DEF_DELAY;
                }
                updateView(AppUtils.toMediaList(response), delay, page);
            }
        });
    }

    private void updateView(final List<MediaEntity> entities, int delay, final int page) {
        //定时器延时刷新接界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page == 1) {
                    view.refreshView(entities);
                } else {
                    view.loadMoreView(entities);
                }
            }
        }, delay);
    }

    @Override
    public void loadMore(int id, int type, int page, int count) {
        loadData(id, type, page, count);
    }

    @Override
    public void refresh(int id, int type, int count) {
        loadData(id, type, 1, count);
    }


}
