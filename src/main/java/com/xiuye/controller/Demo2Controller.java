package com.xiuye.controller;

//import javax.annotation.Resource;

//import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiuye.bean.Demo2User;
import com.xiuye.component.Demo2Properties;
import com.xiuye.util.LogUtil;

@ComponentScan({"com.xiuye.component","com.xiuye.config"})
//@RestController
@SpringBootApplication
public class Demo2Controller {

	@Autowired Demo2Properties props;

	@RequestMapping("/demo")
	String demo(){
		return "Hello";
	}
	@RequestMapping("/demo2user")
	Demo2User user(){
		Demo2User user = new Demo2User();
		user.setName("xiuye");
		user.setAge(10000);
		user.setHobbies(new String[]{"Basketball","Pingpong"});
		return user;
	}
	@RequestMapping("/props")
//	@Resource //not ok
	//@Autowired//not ok
	@Autowired
	@Qualifier("demo2Properties")
	public void props(Demo2Properties props2){

		LogUtil.println(props);
		LogUtil.println(props2);
	}

	public static void main(String[] args) {
		SpringApplication.run(Demo2Controller.class,args);
	}

}
