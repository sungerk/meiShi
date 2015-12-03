package org.sunger.net.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/27.
 */
public class SimpleUserEntity implements Serializable {
    private int id;

    private String screen_name;

    private String avatar;

    private String gender;

    private String age;

    private String constellation;

    private boolean verified;

    private int followers_count;

    private int friends_count;

    private int reposts_count;

    private int videos_count;

    private int real_videos_count;

    private int photos_count;

    private int locked_videos_count;

    private int real_locked_videos_count;

    private int locked_photos_count;

    private int be_liked_count;

    private String url;

    private int created_at;

    private boolean has_password;

    private String status;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getScreen_name() {
        return this.screen_name;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return this.age;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getConstellation() {
        return this.constellation;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean getVerified() {
        return this.verified;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFollowers_count() {
        return this.followers_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public int getFriends_count() {
        return this.friends_count;
    }

    public void setReposts_count(int reposts_count) {
        this.reposts_count = reposts_count;
    }

    public int getReposts_count() {
        return this.reposts_count;
    }

    public void setVideos_count(int videos_count) {
        this.videos_count = videos_count;
    }

    public int getVideos_count() {
        return this.videos_count;
    }

    public void setReal_videos_count(int real_videos_count) {
        this.real_videos_count = real_videos_count;
    }

    public int getReal_videos_count() {
        return this.real_videos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public int getPhotos_count() {
        return this.photos_count;
    }

    public void setLocked_videos_count(int locked_videos_count) {
        this.locked_videos_count = locked_videos_count;
    }

    public int getLocked_videos_count() {
        return this.locked_videos_count;
    }

    public void setReal_locked_videos_count(int real_locked_videos_count) {
        this.real_locked_videos_count = real_locked_videos_count;
    }

    public int getReal_locked_videos_count() {
        return this.real_locked_videos_count;
    }

    public void setLocked_photos_count(int locked_photos_count) {
        this.locked_photos_count = locked_photos_count;
    }

    public int getLocked_photos_count() {
        return this.locked_photos_count;
    }

    public void setBe_liked_count(int be_liked_count) {
        this.be_liked_count = be_liked_count;
    }

    public int getBe_liked_count() {
        return this.be_liked_count;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getCreated_at() {
        return this.created_at;
    }

    public void setHas_password(boolean has_password) {
        this.has_password = has_password;
    }

    public boolean getHas_password() {
        return this.has_password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
