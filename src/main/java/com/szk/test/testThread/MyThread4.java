package com.szk.test.testThread;

import java.util.concurrent.Callable;

public class MyThread4 implements Callable<Object>{

	private Integer taskNum;
	
	public MyThread4(Integer taskNum) {
		this.taskNum = taskNum;
	}
	
	@Override
	public Object call() throws Exception {
		System.out.println("myThread4 is run ------ & the taskNum is :"+taskNum);
		return taskNum;
	}
	
}
