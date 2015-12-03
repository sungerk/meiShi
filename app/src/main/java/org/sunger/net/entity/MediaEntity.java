package org.sunger.net.entity;

/**
 * Created by sunger on 2015/10/27.
 */
public class MediaEntity extends SimpleMediaEntity {
    private String caption;
    private int has_watermark;

    private boolean liked;

    private int plays_count;

    private boolean show_plays_count;


    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return this.caption;
    }


    public void setHas_watermark(int has_watermark) {
        this.has_watermark = has_watermark;
    }

    public int getHas_watermark() {
        return this.has_watermark;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean getLiked() {
        return this.liked;
    }

    public void setPlays_count(int plays_count) {
        this.plays_count = plays_count;
    }

    public int getPlays_count() {
        return this.plays_count;
    }

    public void setShow_plays_count(boolean show_plays_count) {
        this.show_plays_count = show_plays_count;
    }

    public boolean getShow_plays_count() {
        return this.show_plays_count;
    }


}
