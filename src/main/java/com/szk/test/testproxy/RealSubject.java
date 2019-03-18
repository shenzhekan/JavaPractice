package com.szk.test.testproxy;

public class RealSubject implements Person{
	
	public String name;
	private Integer age;
	
	public RealSubject(String name) {
		this.name = name;
	}

	@Override
	public void play() {
		System.out.println("----- "+name+": play -----");
	}

	@Override
	public void eat() {
		System.out.println("----- "+name+": eat -----");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	

}
