package org.sunger.net.ui.adapter;

/**
 * Created by sunger on 2015/10/23.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import org.sunger.net.entity.CategoryEntity;
import org.sunger.net.ui.fragmet.HomeMediasFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments = new ArrayList<>();
    private List<CategoryEntity> mCategoryLists = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    public void addItem(CategoryEntity entity) {
        mCategoryLists.add(entity);
        mFragments.add(HomeMediasFragment.newInstance(entity.getId(), entity.getType()));
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoryLists.get(position).getName();
    }

    /**
     * Override this method to save Fragment state
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }


}