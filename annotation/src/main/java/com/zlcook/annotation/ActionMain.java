package com.zlcook.annotation;


import com.zlcook.annotation.processors.MyTagAnnotationProcessor;
import com.zlcook.annotation.processors.NotNullAnnotationProcessor;

/**
* @author 周亮 
* @version 创建时间：2017年2月17日 上午10:45:33
* 类说明
*/
public class ActionMain{

	public static void main(String[] args) throws ClassNotFoundException {
		//强制类型转换中使用Type Annotation
		String str = "com.zlcook.annotation.Test";
		System.out.println("----------使用MyTagAnnotationProcessor处理器处理："+str);
		MyTagAnnotationProcessor.process(str);
		
		System.out.println("----------使用NotNullAnnotationProcessor处理器处理："+str);
		NotNullAnnotationProcessor.process(str);
			
	}

}
