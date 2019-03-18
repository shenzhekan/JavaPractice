package com.szk.test.base;

import java.util.regex.Pattern;

public class TestEscape {
	
	public static void main(String[] args){
//		test1();
		test2();
	}
	
	public static void test1(){
		String str = "h\\";     // console:  h\   (经过了Java的转义 \\ -> \)
		String expr = "\\";
		expr = "\\\\";          // 这里会先进行Java的转义， \\\\ -> \\ 
		Pattern pattern = Pattern.compile(expr);    // 这里进行正则的转义   \\ -> \
		String str1 = pattern.matcher(str).replaceAll("h");   // 所以这里匹配了一个   \
		System.out.println(str1);
	}
	
	public static void test2(){
		String str = "h//";
		str = "http:\\\\www.baidu.com//";    // http:\\www.baidu.com//
		System.out.println(str+" ----- ");
		String expr = "\\\\";                // 正则匹配 \
		expr = "//";         				 // 正则匹配 //
		str = str.replaceAll(expr, "\\\\");    // 这里先进行Java的转义为 \\ 传入, 在replace中，会再次判断是否要转义，即 \\ -> \
		System.out.println(str);
	}

}
