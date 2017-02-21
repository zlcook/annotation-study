package com.zlcook.annotation.annos;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author 周亮 
* @version 创建时间：2017年2月17日 下午1:35:35
* 类说明
*/
@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {
	String value() default "";
}
