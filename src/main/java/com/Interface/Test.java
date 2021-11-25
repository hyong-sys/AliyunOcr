package com.Interface;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <p>
 * TODO 描述
 * </p>
 *
 * @author hyong
 * @since 2021/10/21
 */
public class Test {
	public static void main(String[] args) {
	new B();
	}
}

class A<T>{
	public A(){
		Class<? extends A> clazz = this.getClass();
		//得到父类（参数化类型），这一步相当于拿到了：A<String>,父类和泛型
		Type genericSuperclass = clazz.getGenericSuperclass();
		//System.out.println(genericSuperclass);                com.Interface.A<java.lang.String>
		//System.out.println(genericSuperclass.getClass());     class sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		//向下转型
		ParameterizedType superClass = (ParameterizedType)genericSuperclass;
		Type[] actualTypeArguments = superClass.getActualTypeArguments();
		Class actualTypeArgument = (Class)actualTypeArguments[0];
		System.out.println(actualTypeArgument);
		System.out.println(clazz.getName());
	}
}

class B extends A<String>{

}

//https://zhuanlan.zhihu.com/p/60966151
