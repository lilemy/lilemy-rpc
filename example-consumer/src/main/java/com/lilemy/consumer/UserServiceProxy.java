package com.lilemy.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.lilemy.common.model.User;
import com.lilemy.common.service.UserService;
import com.lilemy.lilemyrpc.model.RpcRequest;
import com.lilemy.lilemyrpc.model.RpcResponse;
import com.lilemy.lilemyrpc.serializer.JDKSerializer;

import java.io.IOException;

/**
 * 静态代理
 */
public class UserServiceProxy implements UserService {
    @Override
    public String getUserName(User user) {
        // 指定序列化方式
        JDKSerializer serializer = new JDKSerializer();
        // 发送请求
        RpcRequest rpcRequest = RpcRequest
                .builder()
                .serviceName(UserService.class.getName())
                .methodName("getUserName")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            String url = "http://localhost:8080";
            try (HttpResponse httpResponse = HttpRequest.post(url).body(bodyBytes).execute()) {
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (String) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
