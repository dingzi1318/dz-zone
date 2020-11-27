package com.user.event;

import org.springframework.context.ApplicationEvent;

/**
 * 用户更新时间
 *
 * @author dingzi
 */
public class UserUpdateEvent extends ApplicationEvent {

    private Long userId;

    public UserUpdateEvent(Object source) {

        super(source);
    }

    public UserUpdateEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
