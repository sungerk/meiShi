package org.sunger.net.entity;

/**
 * Created by Administrator on 2015/11/30.
 */
public class FollowEntity extends SimpleUserEntity {
    private boolean following;

    private boolean followed_by;

    private String caption;

    private String recommended_caption;

    private int followed_by_at;

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isFollowed_by() {
        return followed_by;
    }

    public void setFollowed_by(boolean followed_by) {
        this.followed_by = followed_by;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getRecommended_caption() {
        return recommended_caption;
    }

    public void setRecommended_caption(String recommended_caption) {
        this.recommended_caption = recommended_caption;
    }

    public int getFollowed_by_at() {
        return followed_by_at;
    }

    public void setFollowed_by_at(int followed_by_at) {
        this.followed_by_at = followed_by_at;
    }


}
