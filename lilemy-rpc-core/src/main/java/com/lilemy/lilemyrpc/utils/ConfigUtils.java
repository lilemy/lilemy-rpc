package com.lilemy.lilemyrpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 * - 读取配置文件并返回配置对象
 */
public class ConfigUtils {

    /**
     * 加载配置对象
     */
    public static <T> T loadConfig(Class<T> clazz, String prefix) {
        return loadConfig(clazz, prefix, "");
    }

    /**
     * 加载配置对象，支持区分环境
     */
    public static <T> T loadConfig(Class<T> clazz, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(clazz, prefix);
    }
}
