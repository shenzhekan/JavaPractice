package com.szk.test.testSerializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerializable {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

//		testSerializable();
		testExternalizable();
		
	}
	
	/**
	 * 测试 Serializable
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void testSerializable() throws FileNotFoundException, IOException, ClassNotFoundException {
		User user = new User();
		user.setName("szk");
		user.setAge(23);
		
		excute(user);
	}
	
	/**
	 * 测试 Externalizable
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public static void testExternalizable() throws FileNotFoundException, ClassNotFoundException, IOException{
		User1 user1 = new User1("user1", 100);
		
		excute(user1);
	}
	
	/**
	 * 执行序列化和反序列化
	 * @param object
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void excute(Object object) throws FileNotFoundException, IOException, ClassNotFoundException{
		System.out.println("----------writeObject:"+object.toString());
		File file = new File("/Users/taofenba/Documents/data/User.txt");
		
		// 将对象序列化到本地文件中
		ObjectOutputStream oStream = 
				new ObjectOutputStream(new FileOutputStream(file));
		oStream.writeObject(object);
		oStream.flush();
		oStream.close();
		
		// 从文件中反序列化出对象		
		ObjectInputStream inputStream = 
				new ObjectInputStream(new FileInputStream(file));
		Object input = inputStream.readObject();
		System.out.println("----------readObject:"+input.toString());
		inputStream.close();
		
		file.delete();
	}

}
