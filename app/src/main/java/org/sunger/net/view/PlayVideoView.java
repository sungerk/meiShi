package org.sunger.net.view;

import org.sunger.net.entity.CommentEntity;
import org.sunger.net.entity.MediaEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/10.
 */
public interface PlayVideoView {

    void showMediaData(MediaEntity mediaEntity);

    void showLoadMediaError();

    void refreshComment(List<CommentEntity> dataList);


    void showMoreComments(List<CommentEntity> dataList);

}
