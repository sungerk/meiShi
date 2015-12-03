package org.sunger.net.presenter;

/**
 * Created by Administrator on 2015/11/10.
 */
public interface PlayVideoPresenter {

    void getMedia(int id);

    void refresh(int page);

    void loadMore(int id, int page);

    void createLikeVideo(int id);

    void destoryLikeVideo(int id);


    void createLikeComment(int id);

    void destoryLikeComment(int id);

}
