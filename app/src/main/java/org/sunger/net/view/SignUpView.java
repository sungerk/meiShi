package org.sunger.net.view;

/**
 * Created by Administrator on 2015/11/18.
 */
public interface SignUpView {

    void showVerifyError(String errorMsg);

    void showVerifySuccerss();


    void showSignUpError(String msg);

    void signUpSuccess();

    void showMsg(String msg);
}
