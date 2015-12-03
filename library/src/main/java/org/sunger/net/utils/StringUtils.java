package org.sunger.net.utils;

/**
 * Created by Administrator on 2015/11/12.
 */
public class StringUtils {

    /**
     * 切割字符串不去除标识字符
     *
     * @param data
     * @param ch
     * @return
     */
    public static String[] splitWithFlag(String data, String ch) {
        String flagString = System.nanoTime() + "";
        data = data.replaceAll(ch, ch + flagString);
        String[] words = data.split(ch);
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll(flagString, ch);
        }
        return words;
    }
}
