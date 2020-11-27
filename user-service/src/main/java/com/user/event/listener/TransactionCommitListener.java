package com.user.event.listener;

import com.user.event.UserUpdateEvent;
import com.user.util.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 数据库事务提交后事件监听
 *
 * @author dingzi
 */
@Component
public class TransactionCommitListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionCommitListener.class);

    @TransactionalEventListener(fallbackExecution = true)
    public void onApplication(ApplicationEvent event) {
        if (event instanceof UserUpdateEvent) {
            LOGGER.info("事务提交后，同步清除ThreadLocal变量");
            ThreadLocalUtil.getUserIdThreadLocalHolder().remove();
        }
    }

}
