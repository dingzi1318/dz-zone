package com.user.configuration;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.user.interceptor.IdempotentInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class WebMvcConfiguration  extends WebMvcConfigurationSupport {

    public static final int MAX_POOL_SIZE = 10;

    public static final int CORE_POOL_SIZE = 10;

    @Bean
    public IdempotentInterceptor idempotentInterceptor() {
        return new IdempotentInterceptor();
    }

    /**
     * 自定义线程池
     *
     * @return
     */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("user-serice-pool-%d").build();
        // 线程名称
        taskExecutor.setThreadFactory(threadFactory);
        // 线程池中常驻的线程数量。核心线程数，默认情况下核心线程会一直存活
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        // 线程池所能容纳的最大线程数。超过这个数的线程将被阻塞
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        // 空闲线程回收时间
        taskExecutor.setKeepAliveSeconds(10);
        // 队列大小
        taskExecutor.setQueueCapacity(1024);
        // 设置拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return taskExecutor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(idempotentInterceptor())
                .addPathPatterns("/users/**")
                .excludePathPatterns("/common/**", "/api/**");

        super.addInterceptors(registry);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
