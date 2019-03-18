package com.szk.test.testproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ClientTest {
	
	public static void main(String[] args){
		
		RealSubject realSubject = new RealSubject("shenzk");
		
		InvocationHandler handler = new DynamicProxy(realSubject);
		
		Person subject = (Person)Proxy.newProxyInstance(
				handler.getClass().getClassLoader(),
				realSubject.getClass().getInterfaces(), 
				handler);
		
		System.out.println("---- proxy:"+subject.getClass().getName());
		subject.eat();
		subject.play();
		
		
	}

}
