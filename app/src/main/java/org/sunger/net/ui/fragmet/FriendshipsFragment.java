package org.sunger.net.ui.fragmet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.sunger.net.entity.SimpleUserEntity;
import org.sunger.net.presenter.FriendshipsPresenter;
import org.sunger.net.presenter.impl.FriendshipsPresenterImpl;
import org.sunger.net.ui.adapter.FriendshipsAdapter;
import org.sunger.net.view.FriendshipsView;

import java.util.List;

import sunger.org.demo.R;


public class FriendshipsFragment extends LoadMoreRecyclerFragemnt implements FriendshipsView {
    private static final String UID_KEY = "uid";
    private RecyclerView mRecyclerView;
    private FriendshipsAdapter mAdapter;
    private FriendshipsPresenter mPresenter;
    private String uid;

    public static Fragment newInatance(int uid) {
        Bundle bundle = new Bundle();
        bundle.putString(UID_KEY, uid + "");
        FriendshipsFragment fragment = new FriendshipsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onFragmentCreate() {
        uid = getArguments().getString(UID_KEY);
        mPresenter = new FriendshipsPresenterImpl(this);
        mRecyclerView = getRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FriendshipsAdapter(getActivity());
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        setAdapter(mAdapter);
        onFragmentLoadMore();
    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.getFriends(uid, getCurrentPage());
    }

    @Override
    public void showError() {
        showLoadError(R.string.msg_error_load);
    }

    @Override
    public void showFriends(List<SimpleUserEntity> data) {
        showMoreData(data);
    }
}
