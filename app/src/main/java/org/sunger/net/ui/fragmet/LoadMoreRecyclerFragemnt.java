package org.sunger.net.ui.fragmet;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sunger.net.support.recyclerview.OnRecycleViewScrollListener;
import org.sunger.net.ui.widget.refresh.BaseLoadMoreRecyclerAdapter;
import org.sunger.net.utils.TimeUtils;

import java.util.List;

import sunger.org.demo.R;


/**
 * Created by sunger on 2015/11/20.
 */
public abstract class LoadMoreRecyclerFragemnt<T> extends BaseFragment {
    protected static final int DEF_DELAY = (int) (1 * 1000);
    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected BaseLoadMoreRecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    protected int currentState = STATE_NORMAL;
    protected long currentTime = 0;
    protected int currentPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_loadmore_recyclerview, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        mRecyclerView.addOnScrollListener(new OnRecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                if (currentState == STATE_NORMAL) {
                    currentState = STATE_LOAD;
                    currentTime = TimeUtils.getCurrentTime();
                    mAdapter.setHasFooter(true);
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                    onFragmentLoadMore();
                } else {
                    getHolder().showMsgInBottom(R.string.msg_waitting_loding);
                }
            }
        });
        onFragmentCreate();
    }

    public void showMoreData(final List<T> dataList) {
        int delay = 0;
        if (TimeUtils.getCurrentTime() - currentTime < DEF_DELAY) {
            delay = DEF_DELAY;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_NORMAL;
                if (dataList.isEmpty()) {
                    mAdapter.setHasMoreDataAndFooter(false, false);
                    getHolder().showMsgInBottom(R.string.msg_no_more_data);
                } else {
                    mAdapter.appendToList(dataList);
                    currentPage++;
                    mAdapter.setHasMoreDataAndFooter(true, false);
                }
                mAdapter.notifyDataSetChanged();
            }
        }, delay);
    }

    public void setAdapter(BaseLoadMoreRecyclerAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }


    public void showLoadError(String errorMsg) {
        mAdapter.setHasFooter(false);
        getHolder().showMsgInBottom(errorMsg);
    }


    public void showLoadError(int errorMsgId) {
        showLoadError(getString(errorMsgId));
    }


    public int getCurrentPage() {
        return currentPage;
    }

    protected abstract void onFragmentLoadMore();

    protected abstract void onFragmentCreate();

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }


}
