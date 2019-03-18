package com.szk.test.testSerializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class User1 implements Externalizable {
	
	private String name;
	private Integer age;
	
	public User1() {}
	
	public User1(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(name+"_md5");
		out.writeInt(age);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		String temp = in.readObject().toString();
		name = temp.substring(0, temp.indexOf("_"));
		age = in.readInt();
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
	
	@Override
	public String toString() {
		return "[name:"+this.name+", age:"+this.age+"]";
	}

}
