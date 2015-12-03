package org.sunger.net.ui.widget;

import android.support.design.widget.TextInputLayout;

/**
 * Created by sunger on 2015/11/17.
 */
public abstract class TextWatcher implements android.text.TextWatcher {
    public String getEditString() {
        return textInputLayout.getEditText().getText().toString();
    }

    private TextInputLayout textInputLayout;

    public TextWatcher(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
