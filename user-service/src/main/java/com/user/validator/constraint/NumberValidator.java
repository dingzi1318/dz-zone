package com.user.validator.constraint;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 数字校验规则注解
 *
 * @author dingzi
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NumberValidator.NumberValidatorInner.class})
public @interface NumberValidator {

    /**
     * 提示
     * @return
     */
    String message() default "非数字，校验失败";

    /**
     * 分组
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 这是啥?
     * @return
     */
    Class<? extends Payload>[] payload() default {};


    class NumberValidatorInner implements ConstraintValidator<NumberValidator, String> {


        /**
         * 初始化
         *
         * @param numberValidator
         */
        @Override
        public void initialize(NumberValidator numberValidator) {
        }

        /**
         * 具体的校验逻辑
         *
         * @param value
         * @param constraintValidatorContext
         * @return
         */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
            return NumberUtils.isCreatable(value);
        }
    }
}
