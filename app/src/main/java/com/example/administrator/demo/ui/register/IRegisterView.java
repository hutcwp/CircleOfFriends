package com.example.administrator.demo.ui.register;

import com.example.administrator.demo.bean.User;
import com.example.administrator.demo.mvp.IBaseView;

/**
 * Created by Administrator on 2018/1/19.
 */

public interface IRegisterView extends IBaseView {

    User getRegisterUser();

    void success();

    void failed();

}
