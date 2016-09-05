package com.test.Controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseController {
	
   ApplicationContext atx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
}
