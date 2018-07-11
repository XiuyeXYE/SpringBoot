package com.xiuye.main;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiuye.banner.MyBanner;
import com.xiuye.util.LogUtil;

//@Profile("production")
//@ImportResource("application.xml")
@SpringBootApplication//include @EnableAutoConfiguration
@RestController
//@PropertySource("config/myconfig.properties")
public class Main {

	@Value("${my_config_info}")
	private String info;

	@Value("${test1-num}")
	private String uuid;

	@Value("${spring.config.name}")
	private String springConfigName;

	@Value("${spring.profiles.active}")
	private String springProfilesActive;

	@PostConstruct
	public void init(){
		LogUtil.println(springConfigName);
		LogUtil.println(springProfilesActive);
		LogUtil.println(info);
		LogUtil.println(uuid);
	}

	@Resource
	private Banner banner;

	@Bean
	public Banner createMyBanner(){
		return new MyBanner();
	}

	@GetMapping("/banner")
	public Banner banner(){
		LogUtil.println(this.banner);
		return this.banner;
	}



	public static void main(String[] args) {

		//model 1 running
//		SpringApplication.run(Main.class);
		//model 2 running
		SpringApplication app = new SpringApplication(Main.class);
//		app.setAddCommandLineProperties(false);
//		app.setBannerMode(Banner.Mode.CONSOLE);
		app.run(args);
	}

}
