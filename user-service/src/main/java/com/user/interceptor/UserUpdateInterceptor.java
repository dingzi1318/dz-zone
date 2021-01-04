package com.user.interceptor;

import com.user.plugin.PluginProcessor;
import com.user.plugin.factory.PluginProcessorFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 用户数据更新拦截器
 *
 * @author dingzi
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class UserUpdateInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserUpdateInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        LOGGER.info("=======进入用户更新拦截器, 当前线程：{}========", Thread.currentThread().getName());
        Object[] args = invocation.getArgs();
        Method method = invocation.getMethod();
        Object target = invocation.getTarget();

        // 维护了一条mapper.xml文件里面 select 、update、delete、insert节点的封装
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // mapperId
        String mappedStatementId = mappedStatement.getId();

        PluginProcessor pluginProcessor = PluginProcessorFactory.create(mappedStatementId);
        if (null != pluginProcessor) {
            pluginProcessor.process(invocation);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
