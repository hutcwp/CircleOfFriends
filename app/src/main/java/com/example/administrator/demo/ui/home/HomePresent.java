package com.example.administrator.demo.ui.home;

import com.example.administrator.demo.mvp.BasePresent;

/**
 * Created by Administrator on 2018/1/19.
 */

public class HomePresent extends BasePresent {

    private IHomeView homeView;


    public HomePresent(IHomeView homeView) {
        this.homeView = homeView;
    }

    void publishComment() {

    }

    void publishReply() {

    }

    public void getTopics() {
        homeView.showTopics();
    }

    void getComments() {

    }

    void getReplys() {

    }

    void getLikesStatus() {

    }

    void updateLikesStatus() {

    }

}
