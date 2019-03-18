package com.szk.test.base;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestParallelStream {

	// 数组容量
	static int ARRAY_LENGTH = 1600000;
	
	public static void main(String[] args) throws InterruptedException {
		
		int poolSize = getPoolNum();
		ArrayList<Person> persons = getArrayList();

		// 多线程
		testMultipleThread(persons, poolSize);
		// 单线程
		testSingleThread(persons, poolSize);
		// Java8特性：ParallelStream 使用 filter
		testStream(persons);
		
	}
	
	/**
	 * 获取随机数据
	 * @return
	 */
	public static ArrayList<Person> getArrayList(){
		ArrayList<Person> persons = new ArrayList<Person>(ARRAY_LENGTH);
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			persons.add(new Person(new Random().nextInt(60)));
		}
		return persons;
	}
	
	/**
	 * 当前机器最大核数*2
	 * @return
	 */
	public static int getPoolNum(){
		int size = Runtime.getRuntime().availableProcessors() * 2;
		System.out.println("Runtime.getRuntime().availableProcessors():" + size / 2);
		return size;
	}
	
	/**
	 * 多线程
	 * @param persons
	 * @param size
	 * @throws InterruptedException
	 */
	public static void testMultipleThread(ArrayList<Person> persons, int size) throws InterruptedException {

		AtomicInteger num = new AtomicInteger();
		ExecutorService pool = Executors.newFixedThreadPool(size);
		int filterSize = ARRAY_LENGTH / size;

		Long startTime = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			final int poolNum = i;
			pool.execute(new Runnable() {

				@Override
				public void run() {
					for (int j = filterSize*poolNum; j < filterSize * (poolNum + 1); j++) {
						if (persons.get(j).getAge() == 18) {
							num.addAndGet(1);
						}
					}
				}
			});
		}
		// 睡两秒，让当前所有线程跑跑完，防止运行太快，导致还未跑完就输出了当前的 num
		// 这里有个问题，当最后一个for跑完，但是时间截止在了跑完for循环时间节点，却不是跑完所有线程的时间节点
		Thread.sleep(1000*2);
		pool.shutdown();
		long time = System.currentTimeMillis() - startTime;

		System.out.println("Time1:" + (time-2000) + ", num1:" + num);

	}
	
	/**
	 * 单线程for循环
	 * @param persons
	 * @param size
	 */
	public static void testSingleThread(ArrayList<Person> persons, int size){
		AtomicInteger num = new AtomicInteger();
		Long startTime = System.currentTimeMillis();
		for(int i = 0; i< ARRAY_LENGTH; i++){
			if(persons.get(i).getAge() == 18){
				num.addAndGet(1);
			}
		}
		long time = System.currentTimeMillis() - startTime;

		System.out.println("Time2:" + time + ", num2:" + num);
	}

	/**
	 * Java8中的新特性 => ParallelStream
	 * @param persons
	 */
	public static void testStream(ArrayList<Person> persons){
		
		Long startTime = System.currentTimeMillis();
		Long streamSize = persons.parallelStream().filter(obj -> obj.getAge() == 18).count();
		long time = System.currentTimeMillis() - startTime;

		System.out.println("Time3:" + time + ", num3:" + streamSize);
	}
}

class Person {
	private Integer age;

	public Person(int age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
