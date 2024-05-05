package com.lilemy.lilemyrpc.serializer;

import java.io.IOException;

/**
 * 序列号接口
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param obj 对象
     * @param <T> 泛型
     * @return 将对象转化为可传输的字节数组
     * @throws IOException IO异常
     */
    <T> byte[] serialize(T obj) throws IOException;

    /**
     * 反序列化
     *
     * @param data  字节数组
     * @param clazz 对象类型
     * @param <T>   泛型
     * @return 对象
     * @throws IOException IO异常
     */
    <T> T deserialize(byte[] data, Class<T> clazz) throws IOException;
}
