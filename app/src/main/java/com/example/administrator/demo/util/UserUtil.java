package com.example.administrator.demo.util;

import com.example.administrator.demo.bean.User;

/**
 * Created by Administrator on 2018/1/15.
 */

public class UserUtil {

    private static User curUser;

    public static User getCurUser() {
        return curUser;
    }

    public static void setCurUser(User user) {
        curUser = user;
    }

}
