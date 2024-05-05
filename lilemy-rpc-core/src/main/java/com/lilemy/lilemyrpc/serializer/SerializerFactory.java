package com.lilemy.lilemyrpc.serializer;

import com.lilemy.lilemyrpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化器工厂
 * - 用于获取序列化器对象
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 序列化映射（用于实现单例）
     */
    public static final Map<String, Serializer> KEY_SERIALIZER_MAP = new HashMap<String, Serializer>() {{
        put(SerializerKeys.JDK, new JDKSerializer());
        put(SerializerKeys.JSON, new JsonSerializer());
        put(SerializerKeys.KRYO, new KryoSerializer());
        put(SerializerKeys.HESSIAN, new HessianSerializer());
    }};

    /**
     * 默认序列化器
     */
    public static final Serializer DEFAULT_SERIALIZER = new JDKSerializer();

    /**
     * 获取实例
     */
    public static Serializer getInstance(String serializerKey) {
        return SpiLoader.getInstance(Serializer.class, serializerKey);
    }
}
