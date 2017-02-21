package com.zlcook.processor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import com.zlcook.processor.annotation.Id;
import com.zlcook.processor.annotation.Persistent;
import com.zlcook.processor.annotation.Property;

/**
* 类说明:hiberante注解处理器，用于根据实体bean的注解生成*.hbm.xml文件，处理时期在编译阶段。
*/
public class HibernateAnnotationProcessor extends AbstractProcessor {

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		// TODO Auto-generated method stub
		super.init(processingEnv);
		System.out.println("HibernateAnnotationProcessor注解处理器初始化完成..............");
	}
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		
	    //定义一个文件输出流，用于生成额外的文件
		PrintStream ps = null;
		try{
			//遍历每个被@Persistent修饰的class文件,使用RoundEnvironment来获取Annotation信息
			for( Element t : roundEnv.getElementsAnnotatedWith(Persistent.class)){
				//获取正在处理的类名
				Name clazzName = t.getSimpleName();
				//获取类定义前的@Persistent Annotation
				Persistent per = t.getAnnotation(Persistent.class);
				//创建文件输出流
				String fileName =clazzName+".hbm.xml";
				ps = new PrintStream(new FileOutputStream(fileName));
				 // 执行输出
			     ps.println("<?xml version=\"1.0\"?>");
			     ps.println("<!DOCTYPE hibernate-mapping");
			     ps.println(" PUBLIC \"-// Hibernate/Hibernate Ma  pping DTD 3.0//EN\"");
			     ps.println(" \"http:// hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">");
			     ps.println("<hibernate-mapping>");
			     ps.print(" <class name=\"" + t);
			     // 输出per的table()的值
			     ps.println("\" table=\"" + per.table() + "\">");
			     //获取@Persistent修改类的各个属性字段。t.getEnclosedElements()获取该Elemet里定义的所有程序单元
			     for(Element ele : t.getEnclosedElements()){
			    	 
			    	 //只处理成员变量上的Annotation，ele.getKind()返回所代表的的程序单元
			    	 if( ele.getKind() == ElementKind.FIELD){
			    		//被id注解修饰的字段
			    		 Id idAno= ele.getAnnotation(Id.class);
				    	 if( idAno != null){
				    		 String column =idAno.column();
				    		 String type =idAno.type();
				    		 String generator = idAno.generator();
				    		 // 执行输出
				    	       ps.println(" <id name=\"" + ele.getSimpleName() + "\" column=\"" + column + "\" type=\"" + type + "\">");
				    	       ps.println(" <generator class=\"" + generator + "\"/>");
				    	       ps.println(" </id>");
				    	 }
				    	 
				    	 //被Property注解修饰的字段
				    	 Property p = ele.getAnnotation(Property.class);
				    	 if( p !=null){
				    		 // 执行输出
				    	     ps.println(" <property name=\"" + ele.getSimpleName() + "\" column=\"" + p.column() + "\"type=\"" + p.type() + "\"/>");
				    	 }
			    	 }
			     }// end for
			     ps.println(" </class>");
			     ps.println("</hibernate-mapping>");
			}// end for 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(ps!=null){
				try{
					ps.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	/** 
     * 这里必须指定，这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称 
     * @return  注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合 
     */  
    @Override  
    public Set<String> getSupportedAnnotationTypes() {  
        Set<String> annotataions = new LinkedHashSet<String>();  
        annotataions.add(Id.class.getCanonicalName());  
        annotataions.add(Property.class.getCanonicalName());  
        annotataions.add(Persistent.class.getCanonicalName());  
        return annotataions;  
    }  
  
    /** 
     * 指定使用的Java版本，通常这里返回SourceVersion.latestSupported()，默认返回SourceVersion.RELEASE_6 
     * @return  使用的Java版本 
     */
    @Override  
    public SourceVersion getSupportedSourceVersion() {  
        return SourceVersion.latestSupported();  
    }  
}
