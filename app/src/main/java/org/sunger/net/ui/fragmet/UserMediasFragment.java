package org.sunger.net.ui.fragmet;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.sunger.net.presenter.UserMediasPresenter;
import org.sunger.net.presenter.impl.UserMediasPresenterImpl;


/**
 * Created by sunger on 2015/10/23.
 */
public class UserMediasFragment extends MediasFragment {
    private UserMediasPresenter mPresenter;

    public static Fragment newInstance(int uid) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_UID, uid);
        Fragment fragment = new UserMediasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        mPresenter = new UserMediasPresenterImpl(this);
        showLoading();
        onFragmentLoadMore();
    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.getMedias(getUid(), getCurrentPage());
    }


}