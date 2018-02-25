package com.example.administrator.demo.ui.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.TopicAdapter;
import com.example.administrator.demo.other.SpacesItemDecoration;
import com.example.administrator.demo.base.BaseActivity;
import com.example.administrator.demo.databinding.ActivityMainBinding;
import com.example.administrator.demo.model.TopicModelImp;
import com.example.administrator.demo.ui.PublishDynamicActivity;

public class HomeActivity extends BaseActivity implements IHomeView{

    private static final String TAG = "HomeActivity";

    private ActivityMainBinding mBinding;

    private HomePresent homePresent;
    private TopicAdapter topicAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mBinding = (ActivityMainBinding) getBinding();
        homePresent= new HomePresent(this);
        mBinding.rvTopic.addItemDecoration(new SpacesItemDecoration(14));
        mBinding.btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(PublishDynamicActivity.class);
            }
        });
        //设置下拉刷新状态栏的颜色
        mBinding.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));
        mBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.swipeRefreshLayout.setRefreshing(true);
               homePresent.getTopics();
            }
        });
    }

    @Override
    protected void loadData() {


    }

    /**
     * 初始化话题列表数据
     */
    private void initData() {
        topicAdapter = new TopicAdapter(HomeActivity.this, TopicModelImp.getInstance().getTopics());
        mBinding.rvTopic.setAdapter(topicAdapter);
        mBinding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showTopics() {
        initData();
    }

    @Override
    public void showComment() {

    }

    @Override
    public void showReply() {

    }

    @Override
    public void showlikes() {

    }

    @Override
    public void updateTopics() {

    }

    @Override
    public void updateComment() {

    }

    @Override
    public void updateReply() {

    }

    @Override
    public void updatelikes() {

    }

    @Override
    public void showNullLayout() {

    }
}
