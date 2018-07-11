package com.xiuye.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiuye.entity.Demo5User;
import com.xiuye.mapper.Demo5UserMapper;
import com.xiuye.util.LogUtil;

@SpringBootApplication
@MapperScan("com.xiuye.mapper")
//@RestController
public class Demo5Controller {

	@Autowired
	private Demo5UserMapper demo5UserMapper;

	@RequestMapping("/allDemo5Users")
	public List<Demo5User> getAllUsers(){
		LogUtil.println(this.demo5UserMapper.demo5Users());
		return this.demo5UserMapper.demo5Users();
	}

	public static void main(String[] args) {
		SpringApplication.run(Demo5Controller.class, args);
	}

}
