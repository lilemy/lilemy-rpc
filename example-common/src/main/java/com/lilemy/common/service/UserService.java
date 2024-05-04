package com.lilemy.common.service;

import com.lilemy.common.model.User;

/**
 * 示例用户服务
 */
public interface UserService {

    /**
     * 获取用户名字
     *
     * @param user 用户对象
     * @return 用户名
     */
    String getUserName(User user);
}
