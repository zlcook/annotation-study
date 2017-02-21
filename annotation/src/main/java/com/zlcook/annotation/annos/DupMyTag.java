package com.zlcook.annotation.annos;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author 周亮 
* @version 创建时间：2017年2月17日 下午12:22:49
* 类说明
*/

@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.METHOD)
public @interface DupMyTag {
    //成员变量为MyTag数组类型
	MyTag[] value();
}
