package com.user.aspect;

import com.user.annotation.PrintTime;
import com.user.annotation.RpcServiceBehavior;
import com.user.dto.ApiResult;
import com.user.dto.RpcResult;
import com.user.exception.RpcBusinessException;
import com.user.exception.WebBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.aop.support.annotation.AnnotationClassFilter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * rpc异常处理切点切面
 *
 * @author dingzi
 */
@Slf4j
@Component
public class RpcExceptionPointCutAdvisor extends DefaultPointcutAdvisor {

    @Override
    public Pointcut getPointcut() {
        return new PrintPointCut();
    }

    @Override
    public Advice getAdvice() {
        return new PrintAdvice();
    }

    private class PrintAdvice implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            Method method = methodInvocation.getMethod();
            try {
                return methodInvocation.proceed();
            } catch (Throwable throwable) {
                if (throwable instanceof RpcBusinessException) {
                    RpcBusinessException rpcBusinessException = (RpcBusinessException) throwable;
                    log.error("执行rpc调用异常, method:{}", method.getName(), rpcBusinessException.getMessage());
                    return RpcResult.fail(rpcBusinessException.getCode(), rpcBusinessException.getMessage());
                } else {
                    return RpcResult.fail("rpc调用异常");
                }
            }
        }
    }

    private class PrintPointCut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return new AnnotationClassFilter(RpcServiceBehavior.class);
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new StaticMethodMatcher() {

                @Override
                public boolean matches(Method method, Class<?> aClass) {
                    return method.getReturnType() == RpcResult.class;
                }
            };

        }
    }
}
