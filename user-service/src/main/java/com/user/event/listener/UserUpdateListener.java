package com.user.event.listener;

import com.user.event.UserUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserUpdateListener {

    private static final Logger logger = LoggerFactory.getLogger(UserUpdateListener.class);

    /**
     * phase = TransactionPhase.AFTER_COMMIT：默认值，只有事务提交之后，才会执行事件监听方法
     * fallbackExecution=true：不存在事务时，也会执行事件监听方法
     *
     * Spring 4.2之后不用实现ApplicationListener接口，重写onApplicationEvent方法
     * 直接使用@EventListner注解、@TransactionalEventListener
     * @param event
     */
    @Async
    @TransactionalEventListener(fallbackExecution = true)
    public void onApplication(ApplicationEvent event) {
        logger.info("用户更新异步时间，当前线程:{}", Thread.currentThread().getName());
        if (event instanceof UserUpdateEvent) {
            logger.info("事务提交后，监听到用户数据更新事件，用户ID：{}，开始执行数据同步至缓存/ES", ((UserUpdateEvent) event).getUserId());
        }
    }
}
