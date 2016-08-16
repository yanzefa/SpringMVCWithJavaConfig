package com.github.izhangzhihao.SpringMVCSeedProject.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 根据角色判断权限
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthByRole {
    AuthorityType[] AuthorityType();
    boolean validate() default true;
}
