package com.xiuye.banner;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import com.xiuye.util.LogUtil;

public class MyBanner implements Banner {

	@Override
	public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {

		LogUtil.println(environment.getDefaultProfiles());
		LogUtil.println(sourceClass);
		LogUtil.println(out);
		out.println("HELLO XIUYE!");

	}

}
