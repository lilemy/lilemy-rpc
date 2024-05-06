package com.lilemy.lilemyrpc.registry;

import com.lilemy.lilemyrpc.spi.SpiLoader;

/**
 * 注册中心工厂
 */
public class RegistryFactory {

    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * 获得实例
     *
     * @param key
     * @return {@link Registry}
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
