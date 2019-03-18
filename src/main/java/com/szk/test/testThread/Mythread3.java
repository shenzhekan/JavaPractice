package com.szk.test.testThread;

import java.util.concurrent.Callable;

public class Mythread3<V> implements Callable<V>{

	@Override
	public V call() throws Exception {
		System.out.println("MyThread3 is run ----------");
		return null;
	}
	
}
