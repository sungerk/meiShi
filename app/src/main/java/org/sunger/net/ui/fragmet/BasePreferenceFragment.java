package org.sunger.net.ui.fragmet;

import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by sunger on 2015/11/28.
 */
public class BasePreferenceFragment extends PreferenceFragment {


    public <T extends Preference> T findPreference(String key) {
        return (T) getPreferenceScreen().findPreference(key);
    }

    public <T extends Preference> T findPreference(int keyRes) {
        return findPreference(getString(keyRes));
    }


}
