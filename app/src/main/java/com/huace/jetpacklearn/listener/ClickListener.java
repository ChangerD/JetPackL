package com.huace.jetpacklearn.listener;

import com.huace.jetpacklearn.entity.User;

/**
 * @author dingjiawei
 * Created on 2019/1/21.
 */
public class ClickListener {

    public void onClick(User user){
        user.setName(user.getName()+Math.random());
    }

}
