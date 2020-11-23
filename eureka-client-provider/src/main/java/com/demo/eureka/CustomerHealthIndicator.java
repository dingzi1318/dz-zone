package com.demo.eureka;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 健康监控
 *
 * @Author dingzi
 * @Date 2020/11/20 10:55
 */
@Component
public class CustomerHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.down();
    }
}
