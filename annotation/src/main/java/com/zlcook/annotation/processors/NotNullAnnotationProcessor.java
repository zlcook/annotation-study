package com.zlcook.annotation.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
* @author 周亮 
* @version 创建时间：2017年2月17日 下午1:58:41
* 类说明 NotNull注解处理器，只处理了implements实现接口出注解、throws声明抛出异常出的注解。
*/

@SuppressWarnings("rawtypes")
public class NotNullAnnotationProcessor {
	
	public static void process(String className) throws ClassNotFoundException{
		try {
			Class clazz =Class.forName(className);
			//获取类继承的、带注解的接口
			AnnotatedType[] aInterfaces =clazz.getAnnotatedInterfaces();
			print(aInterfaces);
			
			Method method = clazz.getDeclaredMethod("foo");
			//获取方法上抛出的带注解的异常
			AnnotatedType[] aExceptions =method.getAnnotatedExceptionTypes();
			print(aExceptions);
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 打印带注解类型
	 * @param array
	 */
	public static void print(AnnotatedType[] array){
		for( AnnotatedType at : array){
			Type type =at.getType();//获取基础类型
			Annotation[] ans =at.getAnnotations();//获取注解
			//打印类型
			System.out.println("注解修饰的类型：");
			System.out.println(type);
			//打印注解
			System.out.println("注解：");
			for( Annotation an : ans){
				System.out.println(an);
			}
		}
	}
}
