package org.sunger.net.view;

import org.sunger.net.entity.FollowEntity;

import java.util.List;

/**
 * Created by sunger on 2015/11/30.
 */
public interface FollowersView {
    void showError();

    void showFollows(List<FollowEntity> data);
}
