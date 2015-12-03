package org.sunger.net.ui.fragmet;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.sunger.net.ui.activity.BaseCompatActivity;

/**
 * Created by sunger on 15/12/1.
 */
public class BaseFragment extends Fragment {


    private BaseCompatActivity holder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseCompatActivity) {
            holder = (BaseCompatActivity) context;
        }
    }

    public BaseCompatActivity getHolder() {
        if (holder == null) {
            throw new IllegalArgumentException("the acticity must be extends BaseCompatActivity");
        }
        return holder;
    }
}
