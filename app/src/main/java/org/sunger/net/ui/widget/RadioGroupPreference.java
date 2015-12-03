package org.sunger.net.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import sunger.org.demo.R;

/**
 * Created by Administrator on 2015/11/27.
 */
public class RadioGroupPreference extends DialogPreference {

    private RadioGroup radioGroup;

    public RadioGroupPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.preference_radiogroup);
    }

    @Override
    protected View onCreateDialogView() {
        View root = super.onCreateDialogView();
        radioGroup = (RadioGroup) root.findViewById(R.id.radioSex);
        return root;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        setSelectedValue(getPersistedString("Male"));
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (!positiveResult) {
            return;
        }

        if (shouldPersist()) {
            String valueToPersist = getSelectedValue();
            if (callChangeListener(valueToPersist)) {
                persistString(valueToPersist);
            }
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    private void setSelectedValue(String value) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View view = radioGroup.getChildAt(i);
            if (view instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) view;
                if (radioButton.getText().equals(value)) {
                    radioButton.setChecked(true);
                }
            }

        }
    }

    private String getSelectedValue() {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonId);
        return (String) radioButton.getText();
    }

}