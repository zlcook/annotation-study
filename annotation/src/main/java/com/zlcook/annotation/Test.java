package com.zlcook.annotation;

import java.io.Serializable;
import java.util.List;

import com.zlcook.annotation.annos.MyTag;
import com.zlcook.annotation.annos.NotNull;

/**
* @author 周亮 
* @version 创建时间：2017年2月17日 上午10:43:32
* 类说明
*/
//implements实现接口中使用Type Annotation
public class Test implements @NotNull(value="Serializable") Serializable{
	
	//泛型中使用Type Annotation  、   抛出异常中使用Type Annotation
	public  void foo() throws @NotNull(value="ClassNotFoundException") ClassNotFoundException {
		//创建对象中使用Type Annotation
		Object obj =new @NotNull String("annotation.Test");
		//强制类型转换中使用Type Annotation
		String str = (@NotNull String) obj;
	}
	
	@MyTag(name="liang")
	@MyTag(name="huan",age =18)
	public void info(){
		
	}
	
	
}
