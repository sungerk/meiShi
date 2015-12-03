package org.sunger.net.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;

import org.sunger.net.presenter.ResetPasswordPresenter;
import org.sunger.net.presenter.impl.ResetPasswordPresenterImpl;
import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.KeyboardUtils;
import org.sunger.net.utils.WidgetUtils;
import org.sunger.net.view.ResetPasswordView;

import sunger.org.demo.R;

/**
 * Created by sunger on 2015/11/20.
 */
public class ResetPasswordActivity extends BaseCompatActivity implements ResetPasswordView, View.OnClickListener {
    private static final int DELAY_MILLIS = 1 * 1000;
    private TextInputLayout mTextInputLayoutPhone;
    private TextInputLayout mTextInputLayoutPassword;
    private EditText mEditTextVerifyCode;
    private Button mButtonSendVerifyCode;
    private ActionProcessButton mButtonSignUp;
    private ResetPasswordPresenter mPresenter;
    private int verifyCodeCountdown = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setUpCommonBackTooblBar(R.id.tool_bar, R.string.title_find_password);
        mPresenter = new ResetPasswordPresenterImpl(this);
        mTextInputLayoutPhone = findView(R.id.textInputLayout_phone);
        mTextInputLayoutPassword = findView(R.id.textInputLayout_password);
        mEditTextVerifyCode = findView(R.id.editText_verify_code);
        mButtonSendVerifyCode = findView(R.id.button_send_verify_code);
        mButtonSendVerifyCode.setOnClickListener(this);
        mButtonSignUp = findView(R.id.buttonSignUp);
        mButtonSignUp.setOnClickListener(this);
        mTextInputLayoutPhone.getEditText().addTextChangedListener(new org.sunger.net.ui.widget.TextWatcher(mTextInputLayoutPhone) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 10)
                    if (FormValidation.isMobile(getEditString())) {
                        mTextInputLayoutPhone.setErrorEnabled(false);
                    } else {
                        setEditTextError(mTextInputLayoutPhone, R.string.msg_error_phone);
                    }
            }
        });
        mTextInputLayoutPassword = findView(R.id.textInputLayout_password);
        mTextInputLayoutPassword.getEditText().addTextChangedListener(new org.sunger.net.ui.widget.TextWatcher(mTextInputLayoutPassword) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 5)
                    if (FormValidation.isSimplePassword(getEditString())) {
                        mTextInputLayoutPassword.setErrorEnabled(false);
                    } else {
                        setEditTextError(mTextInputLayoutPassword, R.string.msg_error_password);
                    }
            }
        });
    }

    public boolean valid(String phone, String password) {
        if (!FormValidation.isMobile(phone)) {
            WidgetUtils.requestFocus(mTextInputLayoutPhone.getEditText());
            setEditTextError(mTextInputLayoutPhone, R.string.msg_error_phone);
            return true;
        }
        if (!FormValidation.isSimplePassword(password)) {
            WidgetUtils.requestFocus(mTextInputLayoutPassword.getEditText());
            setEditTextError(mTextInputLayoutPassword, R.string.msg_error_password);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        KeyboardUtils.hide(this);
        String phone = mTextInputLayoutPhone.getEditText().getText().toString();
        String password = mTextInputLayoutPassword.getEditText().getText().toString();
        if (valid(phone, password))
            return;
        switch (v.getId()) {
            case R.id.button_send_verify_code:
                mPresenter.getVerifySMS(phone, password);
                break;
            case R.id.buttonSignUp:
                String verify_code = mEditTextVerifyCode.getText().toString();
                if (FormValidation.isVerifyCode(verify_code)) {
                    mPresenter.resetPassword(verify_code, phone, password);
                } else {
                    WidgetUtils.requestFocus(mEditTextVerifyCode);
                }
                break;
        }
    }


    @Override
    public void showMsg(String msg) {
        showMsgInBottom(msg);
    }


    @Override
    public void showSuccess() {
        mButtonSignUp.setProgress(100);
    }

    @Override
    public void showResetError(String msg) {
        mButtonSignUp.setProgress(-1);
        showMsgInBottom(msg);
    }

    @Override
    public void showSmsSuccess() {
        mButtonSendVerifyCode.setClickable(false);
        taskHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (verifyCodeCountdown == 0) {
                    mButtonSendVerifyCode.setClickable(true);
                    mButtonSendVerifyCode.setText(R.string.msg_get_verify_code);
                    return;
                }
                mButtonSendVerifyCode.setText(verifyCodeCountdown + getString(R.string.msg_verify_code_point));
                verifyCodeCountdown--;
                taskHandler.postDelayed(this, DELAY_MILLIS);
            }
        }, DELAY_MILLIS);
    }

    private void setEditTextError(TextInputLayout layout, int msgId) {
        layout.setErrorEnabled(true);
        layout.setError(getString(msgId));
    }
}
