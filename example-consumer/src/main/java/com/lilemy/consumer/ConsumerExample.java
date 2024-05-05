package com.lilemy.consumer;

import com.lilemy.common.model.User;
import com.lilemy.common.service.UserService;
import com.lilemy.lilemyrpc.config.RpcConfig;
import com.lilemy.lilemyrpc.proxy.ServiceProxyFactory;
import com.lilemy.lilemyrpc.utils.ConfigUtils;

/**
 * 服务消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpcConfig);

        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();
        user.setName("lilemy-core");
        // 调用
        String userName = userService.getUserName(user);
        if (userName != null) {
            System.out.println(userName);
        } else {
            System.out.println("user == null");
        }
    }

}
