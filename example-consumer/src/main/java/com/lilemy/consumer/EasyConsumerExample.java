package com.lilemy.consumer;

import com.lilemy.common.model.User;
import com.lilemy.common.service.UserService;
import com.lilemy.lilemyrpc.proxy.ServiceProxy;
import com.lilemy.lilemyrpc.proxy.ServiceProxyFactory;

/**
 * 简易服务消费者示例
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        // 静态代理
//        UserService = new UserServiceProxy();
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();
        user.setName("lilemy");
        // 调用
        String userName = userService.getUserName(user);
        if (userName != null) {
            System.out.println(userName);
        } else {
            System.out.println("user == null");
        }
    }
}
