package com.lilemy.provider;

import com.lilemy.common.model.User;
import com.lilemy.common.service.UserService;

/**
 * 示例用户服务实现类
 */
public class UserServiceImpl implements UserService {

    @Override
    public String getUserName(User user) {
        System.out.println("用户名：" + user.getName());
        return user.getName();
    }
}
