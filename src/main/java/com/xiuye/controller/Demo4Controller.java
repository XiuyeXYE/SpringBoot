package com.xiuye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiuye.service.Demo4Service;

@SpringBootApplication
@ComponentScan("com.xiuye.service")
@Controller
public class Demo4Controller {

	@Autowired
	private Demo4Service ds;

	@RequestMapping("/mail")
	public String mail(RedirectAttributes redirectAttributes){
		ds.sendSimpleMail("xiuye_engineer@outlook.com", "Greeting", "Hello!");
		redirectAttributes.addAttribute("message", "OK !");
		return "redirect:/uploadStatus";
	}

	public static void main(String[] args) {
		SpringApplication.run(Demo4Controller.class, args);
	}

}
