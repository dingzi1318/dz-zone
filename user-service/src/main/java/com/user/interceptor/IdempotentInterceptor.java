package com.user.interceptor;

import com.user.annotation.AutoIdempotent;
import com.user.enums.ResultCode;
import com.user.exception.BusinessException;
import com.user.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 幂等性校验拦截器
 *
 * @author dingzi
 */
@Slf4j
public class IdempotentInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ITokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 检查方法是否被@AutoIdempotent注解标记了
        if (!method.isAnnotationPresent(AutoIdempotent.class)) {
            return true;
        }

        // 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
        try {
            return tokenService.checkToken(request);
        } catch (Exception e) {
            throw new BusinessException(ResultCode.REPEAT_REQUEST);
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
