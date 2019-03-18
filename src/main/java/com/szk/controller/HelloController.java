package com.szk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szk.pojo.Person;

@RestController
public class HelloController {

	@RequestMapping(value="/hello")
	public String getHello(){
		return "Hello World!";
	}
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	
	@RequestMapping("/szkTest")
	public String szkTest(){
		return "szkTest";
	}
	
	@RequestMapping("/getPersonJSON")
	public Object getPersonJson(){
		Person person = new Person();
		person.setName("沈哲侃");
		person.setAge(21);
		person.setSex("男");
		person.setDesc("上着课噜噜噜");
		return person;
	}
}
