package org.sunger.net.entity;

import java.io.Serializable;

/**
 * Created by sunger on 2015/10/27.
 */
public class VideoItemEntity implements Serializable {
    private String recommend_caption;

    private String recommend_cover_pic;

    private SimpleMediaEntity media;

    private String type;

    public void setRecommend_caption(String recommend_caption) {
        this.recommend_caption = recommend_caption;
    }

    public String getRecommend_caption() {
        return this.recommend_caption;
    }

    public void setRecommend_cover_pic(String recommend_cover_pic) {
        this.recommend_cover_pic = recommend_cover_pic;
    }

    public String getRecommend_cover_pic() {
        return this.recommend_cover_pic;
    }

    public void setMedia(SimpleMediaEntity media) {
        this.media = media;
    }

    public SimpleMediaEntity getMedia() {
        return this.media;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
