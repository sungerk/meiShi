package org.sunger.net.presenter;

/**
 * Created by Administrator on 2015/10/27.
 */
public interface HomeMediasPresenter {
    void loadMore(int id, int type, int page, int count);
    void refresh(int id, int type, int count);
}
