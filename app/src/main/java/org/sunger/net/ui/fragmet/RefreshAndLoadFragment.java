package org.sunger.net.ui.fragmet;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sunger.net.utils.TimeUtils;

import java.util.List;

import sunger.org.demo.R;

/**
 * Created by sunger on 2015/12/3.
 */
public abstract class RefreshAndLoadFragment<T> extends LoadMoreRecyclerFragemnt implements SwipeRefreshLayout.OnRefreshListener {
    protected final static int STATE_REFRESH = 2;
    private SwipeRefreshLayout mSwipeRefreshWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmet_video_refresh_loadmore_layout, container, false);
    }

    @Override
    protected void onFragmentCreate() {
        mSwipeRefreshWidget = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    public void showRefreshData(final List<T> dataList) {
        int delay = 0;
        if (TimeUtils.getCurrentTime() - currentTime < DEF_DELAY) {
            delay = DEF_DELAY;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_NORMAL;
                mSwipeRefreshWidget.setRefreshing(false);
                currentPage = 2;
                mAdapter.getList().clear();
                mAdapter.appendToList(dataList);
                mAdapter.notifyDataSetChanged();
            }
        }, delay);
    }


    abstract void onRefreshData();

    @Override
    public void onRefresh() {
        if (currentState == STATE_NORMAL) {
            currentState = STATE_REFRESH;
            currentTime = TimeUtils.getCurrentTime();
            mAdapter.setHasFooter(true);
            onRefreshData();
        } else {
            getHolder().showMsgInBottom(R.string.msg_waitting_loding);
        }
    }

    public SwipeRefreshLayout getSwipeRefreshWidget() {
        return mSwipeRefreshWidget;
    }


}
