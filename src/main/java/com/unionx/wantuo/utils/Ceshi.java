package com.unionx.wantuo.utils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Ceshi {
	public static void main(String[] args) {
		ApplicationContext ct = new ClassPathXmlApplicationContext("classpath:root-context.xml");
		System.out.println(ct.getBean("sqlSessionFactory").toString());
	}
}
