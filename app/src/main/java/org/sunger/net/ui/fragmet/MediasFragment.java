package org.sunger.net.ui.fragmet;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.sunger.net.entity.MediaEntity;
import org.sunger.net.ui.activity.VideoPlayActivity;
import org.sunger.net.ui.adapter.MediasAdapter;
import org.sunger.net.view.MediasView;

import java.util.List;

import sunger.org.demo.R;


/**
 * Created by sunger on 2015/10/23.
 */
public abstract class MediasFragment extends LoadMoreRecyclerFragemnt implements MediasView, MediasAdapter.OnItemClickListener {
    protected static final String KEY_UID = "uid";
    private RecyclerView recyclerView;
    private MediasAdapter mAdapter;
    private int uid;

    @Override
    protected void onFragmentCreate() {
        uid = getArguments().getInt(KEY_UID);
        recyclerView = getRecyclerView();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MediasAdapter(getActivity());
        mAdapter.setHasMoreData(true);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        setAdapter(mAdapter);
    }


    public void showLoading() {
        mAdapter.setHasFooter(true);
    }

    public int getUid() {
        return uid;
    }


    @Override
    public void showError() {
        showLoadError(R.string.msg_error_load);
    }


    @Override
    public void showVideo(List<MediaEntity> entities) {
        showMoreData(entities);
    }


    @Override
    public void onItemClick(MediaEntity entity) {
        startActivity(VideoPlayActivity.createIntent(getActivity(), entity.getId()));
    }


}