package org.sunger.net.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/27.
 */
public class SimpleMediaEntity implements Serializable {
    private int id;

    private String video;

    private String url;

    private String cover_pic;

    private int category;

    private int time;

    private boolean is_long;

    private boolean show_controls;

    private String created_at;

    private int comments_count;

    private int likes_count;

    private int reposts_count;

    private boolean is_popular;

    private SimpleUserEntity user;

    private String feed_id;

    private boolean locked;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo() {
        return this.video;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setCover_pic(String cover_pic) {
        this.cover_pic = cover_pic;
    }

    public String getCover_pic() {
        return this.cover_pic;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return this.category;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }

    public void setIs_long(boolean is_long) {
        this.is_long = is_long;
    }

    public boolean getIs_long() {
        return this.is_long;
    }

    public void setShow_controls(boolean show_controls) {
        this.show_controls = show_controls;
    }

    public boolean getShow_controls() {
        return this.show_controls;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getComments_count() {
        return this.comments_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getLikes_count() {
        return this.likes_count;
    }

    public void setReposts_count(int reposts_count) {
        this.reposts_count = reposts_count;
    }

    public int getReposts_count() {
        return this.reposts_count;
    }

    public void setIs_popular(boolean is_popular) {
        this.is_popular = is_popular;
    }

    public boolean getIs_popular() {
        return this.is_popular;
    }

    public void setUser(SimpleUserEntity user) {
        this.user = user;
    }

    public SimpleUserEntity getUser() {
        return this.user;
    }

    public void setFeed_id(String feed_id) {
        this.feed_id = feed_id;
    }

    public String getFeed_id() {
        return this.feed_id;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean getLocked() {
        return this.locked;
    }
}
