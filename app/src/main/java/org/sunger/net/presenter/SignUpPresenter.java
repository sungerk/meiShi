package org.sunger.net.presenter;

/**
 * Created by sunger on 2015/11/18.
 */
public interface SignUpPresenter {
    void signUp(String phone, String pwd, String verify_code);

    void getVerifySMS(String phone, String pwd);

    void autoLogin(String phone, String pwd);
}
