package com.zlcook.annotation.annos;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
* @author 周亮 
* @version 创建时间：2017年2月17日 上午10:42:43
* 类说明
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.METHOD)
@Repeatable(DupMyTag.class)
public @interface MyTag {

	public String name() default "";
	public int age() default 25;
}
