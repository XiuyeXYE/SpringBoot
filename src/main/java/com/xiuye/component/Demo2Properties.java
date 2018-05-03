package com.xiuye.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Demo2Properties {

	@Value("${title}")
	private String title;
	@Value("${content}")
	private String content;
	@Override
	public String toString() {
		return "Demo2Properties [title=" + title + ", content=" + content + "]";
	}


}
