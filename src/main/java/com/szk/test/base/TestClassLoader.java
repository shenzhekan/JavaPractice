package com.szk.test.base;

public class TestClassLoader {
	
	public static void main(String[] args){
		
		test1();
		
	}
	
	public static void test1(){
		
		ClassLoader loader = TestClassLoader.class.getClassLoader();
		System.out.println("Test ClassLoader: " + loader);     // AppClassLoader
		loader = loader.getParent();
		System.out.println("loader's parent: " + loader);       // ExtClassLoader
		loader = loader.getParent();
		System.out.println("loader's parent's parent: " + loader);   // null
		
	}

}
