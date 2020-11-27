package com.user.plugin;

import com.user.annotation.MapperId;
import com.user.context.ApplicationContextProvider;
import com.user.event.UserUpdateEvent;
import com.user.model.UserInfo;
import com.user.util.ThreadLocalUtil;
import org.apache.ibatis.plugin.Invocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@MapperId(id = {"com.user.mapper.UserInfoMapper.insertSelective",
    "com.user.mapper.UserInfoMapper.updateByPrimaryKeySelective"})
@Component
public class UserSavePluginProcessor implements PluginProcessor {

    private static final Logger logger = LoggerFactory.getLogger(UserSavePluginProcessor.class);

    @Override
    public Object process(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        logger.info("进入用户数据插入处理器，当前线程:{}", Thread.currentThread().getName());

        UserInfo userInfo = (UserInfo) invocation.getArgs()[1];

        int result = (int) invocation.proceed();

        // 判断当前线程是否已经处理过用户数据插入--ThreadLocal
        List<Long> localValue = ThreadLocalUtil.getUserIdThreadLocalHolder().get();
        if (!CollectionUtils.isEmpty(localValue) && localValue.contains(userInfo.getId())) {
            logger.info("当前线程已经处理过用户数据插入，用户ID：{}, 不重复处理", userInfo.getId());
            return result;
        }

        // 执行成功，则发布数据同步事件
        if (result > 0) {
            UserUpdateEvent userUpdateEvent = new UserUpdateEvent(this, userInfo.getId());
            ApplicationContextProvider.getApplicationContext().publishEvent(userUpdateEvent);
            // 处理完成，放入ThreadLocal标记改插入事件已经处理
            if (null == localValue) {
                localValue = new ArrayList<>();
            }
            localValue.add(userInfo.getId());
            ThreadLocalUtil.getUserIdThreadLocalHolder().set(localValue);
        }
        return result;
    }
}
