package com.lilemy.lilemyrpc.serializer;

import java.io.*;

/**
 * JDK 序列化器
 */
public class JDKSerializer implements Serializer {

    /**
     * 序列化
     *
     * @param obj 对象
     * @param <T> 泛型
     * @return 将对象转化为可传输的字节数组
     * @throws IOException IO异常
     */
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * 反序列化
     *
     * @param data  字节数组
     * @param clazz 对象类型
     * @param <T>   泛型
     * @return 对象
     * @throws IOException IO异常
     */
    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        try {
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            objectInputStream.close();
        }
    }
}
