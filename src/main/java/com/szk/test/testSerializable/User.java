package com.szk.test.testSerializable;

import java.io.Serializable;

public class User implements Serializable {
	
	/**
	 * serialiVersionUID的作用：
	 * 		为了在序列化和反序列化时比较这个ID，若不同，则会导致反序列化失败
	 * 为什么需要serialVersionID：
	 * 		这个ID值是根据当前类的字段，方法等生成的，若在序列化之后，修改了类，就会导致ID的不同
	 * 		所以需要直接定义了ID值，使得就算修改了当前类，也能够反序列化成功
	 */
	private static final long serialVersionUID = 3201842528895242039L;
	private String name;
	private Integer age;
	
	public User() {}

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
	
	@Override
	public String toString() {
		return "[name:"+this.name+", age:"+this.age+"]";
	}

}
