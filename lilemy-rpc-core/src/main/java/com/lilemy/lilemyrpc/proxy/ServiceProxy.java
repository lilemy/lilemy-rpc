package com.lilemy.lilemyrpc.proxy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.lilemy.lilemyrpc.RpcApplication;
import com.lilemy.lilemyrpc.config.RpcConfig;
import com.lilemy.lilemyrpc.constant.RpcConstant;
import com.lilemy.lilemyrpc.model.RpcRequest;
import com.lilemy.lilemyrpc.model.RpcResponse;
import com.lilemy.lilemyrpc.model.ServiceMetaInfo;
import com.lilemy.lilemyrpc.registry.Registry;
import com.lilemy.lilemyrpc.registry.RegistryFactory;
import com.lilemy.lilemyrpc.serializer.JDKSerializer;
import com.lilemy.lilemyrpc.serializer.Serializer;
import com.lilemy.lilemyrpc.serializer.SerializerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 服务代理 (JDK 动态代理)
 */
public class ServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化请求
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 从注册中心获取服务提供者地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }
            ServiceMetaInfo selectedServiceMetaInfo = serviceMetaInfoList.get(0);

            // 发送请求
            try (HttpResponse httpResponse = HttpRequest
                    .post(selectedServiceMetaInfo.getServiceAddress())
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化响应
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
