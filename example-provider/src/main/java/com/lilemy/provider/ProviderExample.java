package com.lilemy.provider;

import com.lilemy.common.service.UserService;
import com.lilemy.lilemyrpc.RpcApplication;
import com.lilemy.lilemyrpc.config.RegistryConfig;
import com.lilemy.lilemyrpc.config.RpcConfig;
import com.lilemy.lilemyrpc.model.ServiceMetaInfo;
import com.lilemy.lilemyrpc.registry.LocalRegistry;
import com.lilemy.lilemyrpc.registry.Registry;
import com.lilemy.lilemyrpc.registry.RegistryFactory;
import com.lilemy.lilemyrpc.server.HttpServer;
import com.lilemy.lilemyrpc.server.VertxHttpServer;

/**
 * 服务提供者示例
 */
public class ProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
