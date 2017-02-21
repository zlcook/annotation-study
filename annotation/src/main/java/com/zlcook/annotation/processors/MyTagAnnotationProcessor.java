package com.zlcook.annotation.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.zlcook.annotation.annos.MyTag;

/**
* @author 周亮 
* @version 创建时间：2017年2月17日 上午11:02:32
* 类说明
*/

@SuppressWarnings({"unchecked","rawtypes"})
public class MyTagAnnotationProcessor {

	public static void process(String className) throws ClassNotFoundException{
		try {
			Class clazz =Class.forName(className);
			 Annotation[] aArray= clazz.getMethod("info").getAnnotations();
			 for( Annotation an :aArray){
				 System.out.println(an);//打印注解
				 if( an instanceof MyTag){
					 MyTag tag = (MyTag) an;
					 System.out.println("tag.name():"+tag.name());
					 System.out.println("tag.age():"+tag.age());
				 }
			 }
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
