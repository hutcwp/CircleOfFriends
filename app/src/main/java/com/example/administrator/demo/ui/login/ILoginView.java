package com.example.administrator.demo.ui.login;

import com.example.administrator.demo.mvp.IBaseView;

/**
 * Created by Administrator on 2018/1/19.
 */

public interface ILoginView extends IBaseView {

    String getPhone();

    String getPassword();

    void showSuccess();

    void showFailed();

}
