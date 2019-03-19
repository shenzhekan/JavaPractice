package com.question;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution1 {
	// 数组容量
	static int ARRAY_LENGTH = 1600000;

	public static void main(String[] args) throws Exception {

		int poolSize = getPoolNum();
		ArrayList<Person> persons = getArrayList();

		// 多线程
		System.out.print("多线程：");
		testMultipleThread(persons, poolSize);
		// 单线程
		System.out.print("单线程：");
		testSingleThread(persons, poolSize);
		// Java8特性：ParallelStream 使用 filter
		System.out.print("Java8(ParallelStream)：");
		testStream(persons);
		// 分组任务线程
		System.out.print("分组线程：");
		testGroupThread(persons, poolSize);

	}

	/**
	 * 获取随机数据
	 * 
	 * @return
	 */
	public static ArrayList<Person> getArrayList() {
		ArrayList<Person> persons = new ArrayList<Person>(ARRAY_LENGTH);
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			persons.add(new Person(new Random().nextInt(60), new Random().nextInt(60) + ""));
		}
		return persons;
	}

	/**
	 * 当前机器最大核数*2
	 * 
	 * @return
	 */
	public static int getPoolNum() {
		int size = Runtime.getRuntime().availableProcessors() * 2;
		System.out.println("Runtime.getRuntime().availableProcessors():" + size / 2);
		return size;
	}

	/**
	 * 多线程
	 * 
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
					for (int j = filterSize * poolNum; j < filterSize * (poolNum + 1); j++) {
						if (persons.get(j).getAge() == 18) {
							num.addAndGet(1);
						}
					}
				}
			});
		}
		// 睡两秒，让当前所有线程跑跑完，防止运行太快，导致还未跑完就输出了当前的 num
		// 这里有个问题，当最后一个for跑完，但是时间截止在了跑完for循环时间节点，却不是跑完所有线程的时间节点
		Thread.sleep(1000 * 2);
		pool.shutdown();
		long time = System.currentTimeMillis() - startTime;

		System.out.println("Time1:" + (time - 2000) + ", num1:" + num);

	}

	/**
	 * 单线程for循环
	 * 
	 * @param persons
	 * @param size
	 */
	public static void testSingleThread(ArrayList<Person> persons, int size) {
		AtomicInteger num = new AtomicInteger();
		Long startTime = System.currentTimeMillis();
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			if (persons.get(i).getAge() == 18) {
				num.addAndGet(1);
			}
		}
		long time = System.currentTimeMillis() - startTime;

		System.out.println("Time2:" + time + ", num2:" + num);
	}

	/**
	 * Java8中的新特性 => ParallelStream
	 * 
	 * @param persons
	 */
	public static void testStream(ArrayList<Person> persons) {

		Long startTime = System.currentTimeMillis();
		Long streamSize = persons.parallelStream().filter(obj -> obj.getAge() == 18).count();
		long time = System.currentTimeMillis() - startTime;

		System.out.println("Time3:" + time + ", num3:" + streamSize);
	}

	/**
	 * 线程分组
	 * 
	 * @param persons
	 * @param size
	 * @throws Exception
	 */
	public static void testGroupThread(ArrayList<Person> persons, int size) throws Exception {

		Long startTime = System.currentTimeMillis();
		ExecutorService pool = Executors.newFixedThreadPool(size);
		int[] count = new int[size];
		int filterSize = ARRAY_LENGTH / size;
		for (int i = 0; i < size; i++) {
			Future<Integer> future = pool.submit(new MyTask(persons, i * filterSize, (i + 1) * filterSize));
			count[i] = future.get();
		}
		Long streamSize = 0l;
		for (int i = 0; i < count.length; i++) {
			streamSize += count[i];
		}
		pool.shutdown();
		long time = System.currentTimeMillis() - startTime;
		System.out.println("Time4:" + time + ", num4:" + streamSize);
	}

}

class Person {
	private Integer age;
	private String name;

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

// 任务线程
class MyTask implements Callable<Integer> {
	public ArrayList<Person> persons;
	public int start;
	public int end;

	public MyTask(ArrayList<Person> persons, int start, int end) {
		this.persons = persons;
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() throws Exception {
		return count(persons, start, end);
	}

	public Integer count(ArrayList<Person> persons, int start, int end) {
		Integer result = 0;
		for (int i = start; i < end; i++) {
			if (persons.get(i).getAge() == 18) {
				result++;
			}
		}
		return result;
	}
}
