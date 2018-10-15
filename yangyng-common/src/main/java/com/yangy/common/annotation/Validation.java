package com.yangy.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validation {

    boolean notNull() default true;

    boolean isValidate() default true;

    boolean isForm() default false;

}
