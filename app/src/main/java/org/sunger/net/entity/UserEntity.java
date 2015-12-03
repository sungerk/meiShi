package org.sunger.net.entity;

/**
 * Created by Administrator on 2015/10/27.
 */
public class UserEntity extends SimpleUserEntity {
    private String birthday;

    private boolean following;

    private boolean followed_by;

    private boolean blocking;

    private boolean blocked_by;

    private String weibo_share_caption;

    private String facebook_share_caption;

    private String weixin_share_caption;

    private String weixin_friendfeed_share_caption;

    private String qzone_share_caption;

    private String qq_share_caption;

    private String instagram_share_caption;


    private String share_pic;

    private String description;

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

    public boolean isBlocking() {
        return blocking;
    }

    public void setBlocking(boolean blocking) {
        this.blocking = blocking;
    }

    public boolean isBlocked_by() {
        return blocked_by;
    }

    public void setBlocked_by(boolean blocked_by) {
        this.blocked_by = blocked_by;
    }

    public String getWeibo_share_caption() {
        return weibo_share_caption;
    }

    public void setWeibo_share_caption(String weibo_share_caption) {
        this.weibo_share_caption = weibo_share_caption;
    }

    public String getFacebook_share_caption() {
        return facebook_share_caption;
    }

    public void setFacebook_share_caption(String facebook_share_caption) {
        this.facebook_share_caption = facebook_share_caption;
    }

    public String getWeixin_share_caption() {
        return weixin_share_caption;
    }

    public void setWeixin_share_caption(String weixin_share_caption) {
        this.weixin_share_caption = weixin_share_caption;
    }

    public String getWeixin_friendfeed_share_caption() {
        return weixin_friendfeed_share_caption;
    }

    public void setWeixin_friendfeed_share_caption(String weixin_friendfeed_share_caption) {
        this.weixin_friendfeed_share_caption = weixin_friendfeed_share_caption;
    }

    public String getQzone_share_caption() {
        return qzone_share_caption;
    }

    public void setQzone_share_caption(String qzone_share_caption) {
        this.qzone_share_caption = qzone_share_caption;
    }

    public String getQq_share_caption() {
        return qq_share_caption;
    }

    public void setQq_share_caption(String qq_share_caption) {
        this.qq_share_caption = qq_share_caption;
    }

    public String getInstagram_share_caption() {
        return instagram_share_caption;
    }

    public void setInstagram_share_caption(String instagram_share_caption) {
        this.instagram_share_caption = instagram_share_caption;
    }

    public String getShare_pic() {
        return share_pic;
    }

    public void setShare_pic(String share_pic) {
        this.share_pic = share_pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


}
