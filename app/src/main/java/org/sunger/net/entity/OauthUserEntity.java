package org.sunger.net.entity;

import java.io.Serializable;

/**
 * Created by sunger on 2015/11/17.
 */
public class OauthUserEntity implements Serializable {

    private String access_token;

    private String expires_at;

    private UserEntity user;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public String getExpires_at() {
        return this.expires_at;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return this.user;
    }

}
