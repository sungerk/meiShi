package org.sunger.net.ui.fragmet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.sunger.net.entity.FollowEntity;
import org.sunger.net.presenter.FollowersPresenter;
import org.sunger.net.presenter.impl.FollowersPresenterImpl;
import org.sunger.net.ui.adapter.FollowsAdapter;
import org.sunger.net.view.FollowersView;

import java.util.List;

import sunger.org.demo.R;


public class FollowersFragment extends LoadMoreRecyclerFragemnt implements FollowersView {
    private static final String UID_KEY = "uid";
    private RecyclerView mRecyclerView;
    private FollowsAdapter mAdapter;
    private FollowersPresenter mPresenter;
    private String uid;

    public static Fragment newInatance(int uid) {
        Bundle bundle = new Bundle();
        bundle.putString(UID_KEY, uid + "");
        FollowersFragment fragment = new FollowersFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onFragmentCreate() {
        uid = getArguments().getString(UID_KEY);
        mPresenter = new FollowersPresenterImpl(this);
        mRecyclerView = getRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FollowsAdapter(getActivity());
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        setAdapter(mAdapter);
        // first automatic loading
        mAdapter.setHasFooter(true);
        onFragmentLoadMore();
    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.getFollowers(uid, getCurrentPage());
    }

    @Override
    public void showError() {
        showLoadError(getString(R.string.msg_error_load));
    }

    @Override
    public void showFollows(List<FollowEntity> data) {
        showMoreData(data);
    }

}
