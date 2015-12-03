package org.sunger.net.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by sunger on 2015/11/17.
 */
public class FormValidation {

    /**
     * 判断是不是验证码
     *
     * @param code
     * @return
     */
    public static boolean isVerifyCode(String code) {
        return validation("\\d{6}", code);
    }

    public static boolean isMobile(String phoneNum) {
        // 如果手机中有+86则会自动替换掉
        return validation("^[1][3,4,5,7,8][0-9]{9}$",
                phoneNum.replace("+86", ""));
    }


    public static boolean isUserName(String username) {
        /***
         * 正则表达式为：^[a-z0-9_-]{3,15}$ 各部分作用如下： [a-z0-9_-] -----------
         * 匹配列表中的字符，a-z,0–9,下划线，连字符 {3,15}-----------------长度至少3个字符，最大长度为15
         * 如果有不同需求的可以参考以上修改正则表达式
         */
        return validation("^[a-z0-9_-]{3,15}$", username);
    }


    public static boolean isPassword(String pwd) {
        /**
         * ^ 匹配一行的开头位置(?![0-9]+$) 预测该位置后面不全是数字
         * (?![a-zA-Z]+$) 预测该位置后面不全是字母
         * [0-9A-Za-z] {6,16} 由6-16位数字或这字母组成
         */
        return validation("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", pwd);
    }

    public static boolean isSimplePassword(String pwd) {
        /**
         * ^ 匹配一行的开头位置(?![0-9]+$) 预测该位置后面不全是数字
         * (?![a-zA-Z]+$) 预测该位置后面不全是字母
         * [0-9A-Za-z] {6,16} 由6-16位数字或这字母组成
         */
        return validation("^[0-9a-zA-Z]{6,16}$", pwd);
    }


    public static boolean isEmail(String mail) {
        return validation(
                "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
                mail);
    }


    /**
     * 正则校验
     *
     * @param str 需要校验的字符串
     * @return 验证通过返回true
     */
    public static boolean validation(String pattern, String str) {
        if (TextUtils.isEmpty(str))
            return false;
        return Pattern.compile(pattern).matcher(str).matches();
    }

}
