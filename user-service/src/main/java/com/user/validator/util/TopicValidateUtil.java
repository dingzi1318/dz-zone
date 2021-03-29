package com.user.validator.util;

import com.user.dto.ApiResult;
import org.hibernate.validator.HibernateValidator;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 话题校验工具类
 *
 * @Author dingzi
 * @Date 2020/5/22 16:00
 */
public final class TopicValidateUtil {

    private TopicValidateUtil() {

    }

    /**
     * 开启快速结束模式 failFast (true)
     */
    private static final Validator VALIDATOR = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(false)
            .buildValidatorFactory()
            .getValidator();

    /**
     * 校验对象
     *
     * @param t      bean
     * @param groups 校验组
     * @return FeJsonResult 前端返回数据结构
     */
    public static <T> ApiResult validateBean(T t, Class<?>... groups) {
        Set<ConstraintViolation<T>> violationSet = VALIDATOR.validate(t, groups);
        if (CollectionUtils.isEmpty(violationSet)) {
            return null;
        }
        String invalidMessage = violationSet.stream().findFirst()
                .map(ConstraintViolation::getMessage).orElse("话题参数校验失败");
        return ApiResult.fail(invalidMessage);
    }

    /**
     * 校验bean的某一个属性
     *
     * @param obj          bean
     * @param propertyName 属性名称
     * @return FeJsonResult
     */
    public static <T> ApiResult validateProperty(T obj, String propertyName) {
        Set<ConstraintViolation<T>> violationSet = VALIDATOR.validateProperty(obj, propertyName);
        if (CollectionUtils.isEmpty(violationSet)) {
            return null;
        }
        String invalidMessage = violationSet.stream().findFirst()
                .map(ConstraintViolation::getMessage).orElse("话题参数校验失败");
        return ApiResult.fail(invalidMessage);
    }
}
