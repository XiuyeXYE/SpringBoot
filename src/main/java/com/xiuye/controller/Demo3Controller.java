package com.xiuye.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiuye.util.LogUtil;

@SpringBootApplication
@Controller
@ControllerAdvice
public class Demo3Controller extends SpringBootServletInitializer{

	@RequestMapping("/hello")
	//必须添加pom.xml中的配置,否则无法映射
	public String hello(Model model,@RequestParam(value="name",required=false,defaultValue="world")String name){

		model.addAttribute("name", name);

		return "OK";
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.sources(Demo3Controller.class);
	}


	public static void main(String[] args) {

		SpringApplication.run(Demo3Controller.class, args);

	}

	@ExceptionHandler(MultipartException.class)
	public String handlerError(MultipartException e, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
		return "redirect:/uploadStatus";
	}

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file")MultipartFile file,RedirectAttributes redirectAttributes) throws IOException{
		if(file.isEmpty()){
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:/uploadStatus";
		}

		LogUtil.println("File : " + file.getOriginalFilename());
		byte[] bytes = file.getBytes();
		Path path = Paths.get(file.getOriginalFilename());
		Files.write(path, bytes);
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
		return "redirect:/uploadStatus";
	}

	@GetMapping("/form")
	public String form(){
		return "upload";
	}
	@GetMapping("/uploadStatus")
	public String uploadStatus(){
		return "uploadStatus";
	}

}
