package com.example.administrator.demo.ui.home;

import com.example.administrator.demo.mvp.IBaseView;

/**
 * Created by Administrator on 2018/1/19.
 */

public interface IHomeView extends IBaseView {

    void showTopics();

    void showComment();

    void showReply();

    void showlikes();

    void updateTopics();

    void updateComment();

    void updateReply();

    void updatelikes();

    void showNullLayout();

}
