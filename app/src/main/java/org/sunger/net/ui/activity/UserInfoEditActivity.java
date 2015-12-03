package org.sunger.net.ui.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.sunger.net.ui.fragmet.UserInfoEditFragment;

import sunger.org.demo.R;

/**
 * Created by sunger on 15/11/26.
 */
public class UserInfoEditActivity extends BaseCompatActivity {
    private UserInfoEditFragment mUserInfoEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_edit);
        setUpCommonBackTooblBar(R.id.tool_bar, R.string.title_edit_user_info);
        if (savedInstanceState == null) {
            replaceFragment(R.id.view_holder, mUserInfoEditFragment = UserInfoEditFragment.newInatance());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mUserInfoEditFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_edit_user_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            mUserInfoEditFragment.save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(int viewId, android.app.Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }


}
