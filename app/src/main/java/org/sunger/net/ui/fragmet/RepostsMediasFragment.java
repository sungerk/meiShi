package org.sunger.net.ui.fragmet;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.sunger.net.presenter.RepostsMediasPresenter;
import org.sunger.net.presenter.impl.RepostsMediasPresenterImpl;


/**
 * Created by sunger on 2015/10/23.
 */
public class RepostsMediasFragment extends MediasFragment {
    private RepostsMediasPresenter mPresenter;

    public static Fragment newInstance(int uid) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_UID, uid);
        Fragment fragment = new RepostsMediasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        mPresenter = new RepostsMediasPresenterImpl(this);
        showLoading();
        onFragmentLoadMore();
    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.getRepostsMedias(getUid(), getCurrentPage());
    }


}