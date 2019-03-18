package com.szk.test.base;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;

public class TestOverload {
	
	public static void main(String[] args){
		test1();
//		test2();
	}
	
	public static void test1(){
		method(1);
		method((short)1);
		method((long)1);
		method((char)65);
	}
	
//	public static void method(int a){
//		System.err.println("int:"+a);
//	}
//	public static void method(short a){
//		System.err.println("short:"+a);
//	}
//	public static void method(char a){
//		System.err.println("char:"+a);
//	}
	public static void method(long a){
		System.err.println("long:"+a);
	}
	
	// ------------------------------
	
	public static void test2(){
		say(new Object());
		say(new A());
		say(new B());
		say(new C());
	}
	
	public static class A{
		
	}
	public static class B extends A{
		
	}
	public static class C extends A{
		
	}
	
	public static void say(Object obj){
		System.out.println("obj");
	}
	public static void say(A a){
		System.out.println("A");
	}
	public static void say(B b){
		System.out.println("B");
	}
	public static void say(C c){
		System.out.println("C");
	}
	

}
