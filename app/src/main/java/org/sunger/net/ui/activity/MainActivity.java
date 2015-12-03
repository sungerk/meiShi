package org.sunger.net.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.sunger.net.entity.CategoryEntity;
import org.sunger.net.presenter.CategoryPresenter;
import org.sunger.net.presenter.impl.CategoryPresenterImpl;
import org.sunger.net.ui.adapter.FragmentAdapter;
import org.sunger.net.view.CategoryView;

import sunger.org.demo.R;

public class MainActivity extends BaseCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CategoryView {
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentAdapter mAdapter;
    private CategoryPresenter mPresenter;
    private MainNavigationHeader mMainNavigationHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkNetWork();
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mTabLayout = (TabLayout) this.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) this.findViewById(R.id.view_pager);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        intNavigationView();
        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mPresenter = new CategoryPresenterImpl(this);
        mPresenter.getCategory();
    }

    private void intNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mMainNavigationHeader = new MainNavigationHeader(this, mNavigationView);
        mMainNavigationHeader.bindData();
    }


    public void showMsgBelowTabLayout(String msg) {
        showPopWindwow(mTabLayout, msg);
    }


    @Override
    public void showError() {
        showMsgInBottom(R.string.msg_error_network);
    }

    @Override
    public void addCategoryInfo(CategoryEntity entity) {
        mAdapter.addItem(entity);
        mTabLayout.addTab(mTabLayout.newTab());
    }

    @Override
    public void bindCategoryData() {
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainNavigationHeader.bindData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
