package com.szk.test.testThread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Test {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
//		extendsThread();
//		implementsRunable();
//		implementsCallable();
//		ExecutorServiceAndFutureAndCallable();
		testTimer();
	}
	
	/**
	 * 通过继承Thread 类实现线程
	 */
	public static void extendsThread(){
		Thread thread = new MyThread();
		thread.run();
	}
	
	/**
	 * 通过实现Runnable 来实现线程
	 */
	public static void implementsRunable(){
		Thread thread = new Thread(new MyThread2());
		thread.run();
	}
	
	/**
	 * 通过实现 Callable 的线程是有返回值的， V
	 * 通过实现 Callable 来创建线程 myThread3
	 * 通过线程实例来创建FutureTask
	 * 痛殴FutureTask实例来创建线程
	 */
	public static void implementsCallable(){
		// 实现接口 Callable 的线程实例
		Mythread3<Integer> mythread3 = new Mythread3<Integer>();
		// 通过 myThread3 来创建 FutureTask 对象
		FutureTask<Integer> futureTask = new FutureTask<Integer>(mythread3);
		// 通过 futureTask 来创建线程
		Thread thread = new Thread(futureTask);
		thread.run();
 	}
	
	/**
	 * 通过实现 Callable 的线程是有返回值的 V
	 * 通过实现 Callable ，使用线程池来创建线程
	 * 1. MyThread4 实现来接口 Callable 
	 * 2. 使用ExecutorService pool = Executors.newFixedThreadPool(5); 来创建 线程池
	 * 3. 使用 pool.submit(new MyThread4(i)) 来执行任务并获取 Future 对象
	 * 4. 通过 future.get(); 来获取线程的返回值
	 * 5. 注意！！！这里的future.get() 是线程阻塞的，当线程没有返回值时，会一直等待
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void ExecutorServiceAndFutureAndCallable() throws InterruptedException, ExecutionException{
		Date date1 = new Date();
		Integer maxTaskNum = 5;
		
		// 创建一个线程池（5）
		ExecutorService pool = Executors.newFixedThreadPool(maxTaskNum);
		// Future 集合，用于存放多个线程返回的 Future 对象
		List<Future> futures = new ArrayList<Future>();
		
		for(int i = 0; i < maxTaskNum; i++){
			MyThread4 thread4 = new MyThread4(i);
			// 提交当前线程，并返回一个 Future 对象， 可通过 future.get() 来获取线程的返回值
			Future<Object> future = pool.submit(thread4);
			futures.add(future);
		}

		for(Future f: futures){
			System.out.println("loop the futures: "+ f.get());
		}
		
		Date date2 = new Date();
		System.out.println("耗时（毫秒）--- "+(date2.getTime() - date1.getTime()));
		
		
	}
	
	/**
	 * Timer 定时器创建线程
	 */
	public static void  testTimer(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Timer is run ...");
			}
		}, new Date(), 5*1000);
	}

}
