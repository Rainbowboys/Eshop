package com.test.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	@Value("#{configProperties['pic_upload_path']}")
	private String pic_upload_path;
	ApplicationContext atx = new ClassPathXmlApplicationContext(
			"classpath:applicationContext.xml");
	
}
