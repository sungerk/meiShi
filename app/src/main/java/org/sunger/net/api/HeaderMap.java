package org.sunger.net.api;

import org.sunger.net.app.App;
import org.sunger.net.app.AppConstants;
import org.sunger.net.entity.OauthUserEntity;

import java.util.HashMap;

/**
 * Created by sunger on 15/11/21.
 */
public class HeaderMap extends HashMap<String, String> {
    public HeaderMap() {
        OauthUserEntity entity= App.getInstance().getOauthUserEntity();
        if (entity != null) {
            put(AppConstants.ParamKey.ACCESS_TOKEN_KEY, entity.getAccess_token());
        }
    }
}
