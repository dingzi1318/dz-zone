package com.user.plugin.factory;

import com.user.annotation.MapperId;
import com.user.context.ApplicationContextProvider;
import com.user.plugin.PluginProcessor;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Map;

/**
 * 插件处理器工厂
 *
 * @author dingzi
 */
public class PluginProcessorFactory {


    public static PluginProcessor create(String mapperId) {
        // 根据注解获取bean
        Map<String, Object> beansWithAnnotation = ApplicationContextProvider.getBeansWithAnnotation(MapperId.class);
        PluginProcessor processor = (PluginProcessor) beansWithAnnotation.values().stream().filter(k -> {
            if (k instanceof PluginProcessor) {
                MapperId annotation = k.getClass().getAnnotation(MapperId.class);
                boolean isMatched = Arrays.stream(annotation.id()).anyMatch(t -> t.equals(mapperId));
                return isMatched;
            }
            return false;
        }).findFirst().orElse(null);
        return processor;
    }


}
