package com.example.administrator.demo.interfaze;

import com.example.administrator.demo.bean.User;

/**
 * Created by Administrator on 2018/1/15.
 */

public interface IUserModel {

    boolean login(String phone, String password);

    boolean register(User user);

    User getUserById(int userId);
}
