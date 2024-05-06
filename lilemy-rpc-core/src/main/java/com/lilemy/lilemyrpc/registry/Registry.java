package com.lilemy.lilemyrpc.registry;

import com.lilemy.lilemyrpc.config.RegistryConfig;
import com.lilemy.lilemyrpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册中心
 */
public interface Registry {

    /**
     * 初始化
     *
     * @param registryConfig RPC 框架注册中心配置
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务（服务端）
     *
     * @param serviceMetaInfo 服务信息
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务（服务端）
     *
     * @param serviceMetaInfo 服务信息
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现
     * - 获取某服务的所有节点（消费端）
     *
     * @param serviceKey 服务键名
     * @return {@link List<ServiceMetaInfo>}
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();

}
