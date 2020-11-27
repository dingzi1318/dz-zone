package com.user.plugin;

import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * mybatis插件处理器
 *
 */
public interface PluginProcessor {

    Object process(Invocation invocation) throws InvocationTargetException, IllegalAccessException;
}
