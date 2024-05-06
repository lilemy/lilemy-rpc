package com.lilemy.lilemyrpc.config;

import lombok.Data;

/**
 * RPC 框架注册中心配置
 */
@Data
public class RegistryConfig {

    /**
     * 注册中心类型
     */
    private String registry = "etcd";

    /**
     * 注册中心地址
     */
    private String address = "http://loaclhost:2300";

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间（单位毫秒）
     */
    private Long timeout = 10000L;

}
