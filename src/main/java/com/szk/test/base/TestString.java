package com.szk.test.base;

import java.util.Collections;
import java.util.concurrent.FutureTask;

public class TestString {
	
	public static void main(String[] args){
//		test1();
//		test2();
//		test3();
		test4();
	}
	
	public static void test1(){
		String str = "__/seg/";
		System.out.println(str.length());
		str = str.substring(0,str.length()-1);
		System.out.println(str);
	}
	
	public static void  test2() {
		String string  = "szk/qwe/qwe/qwe/qwe";
		int index = string.indexOf("/", string.indexOf("/")+1);
		System.out.println(index+"------");
	}
	
	public static void test3(){
		String[] strings = {};
		System.out.println(strings.length);
	}
	
	public static void test4(){
		B b = new B();
		b.setString("sdf");
		if(b.getStr1()!=null){
			System.out.println("ok");
		}else{
			System.out.println("null");
		}
	}
	
}

class A {
	private String[] str1;

	public String[] getStr1() {
		return str1;
	}

	public void setStr1(String[] str1) {
		this.str1 = str1;
	}
	
}
class B extends A{
	private String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	
}
