package org.sunger.net.view;

import org.sunger.net.entity.SimpleUserEntity;

import java.util.List;

/**
 * Created by sunger on 2015/11/30.
 */
public interface FriendshipsView {
    void showError();

    void showFriends(List<SimpleUserEntity> data);
}
