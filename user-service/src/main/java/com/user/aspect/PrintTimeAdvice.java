package com.user.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PrintTimeAdvice {

    @Pointcut(value = "@annotation(com.user.annotation.PrintTime)")
    public void printTime() {}

    @Around("printTime()")
    public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed(args);
        stopWatch.stop();

//        ServletRequestAttributes sra =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = sra.getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        log.info("方法：{}， 耗时：{}", methodName, stopWatch.getTime());
        return result;
    }

}
