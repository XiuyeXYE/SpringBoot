package com.xiuye.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

import com.xiuye.util.LogUtil;

//@Configuration
@SpringBootConfiguration
public class Demo2Config {

	public class MyFilter implements Filter {

		@Override
		public void destroy() {
			LogUtil.println("Filter Destroyed");
		}

		@Override
		public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
				throws IOException, ServletException {
			LogUtil.println(arg0);
			LogUtil.println(arg1);
			HttpServletRequest req = (HttpServletRequest) arg0;
			LogUtil.println("Url ? : " + req.getRequestURI());
			arg2.doFilter(arg0, arg1);// 传递下一层!
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
			LogUtil.println("MyFilter Initialize:");
			Enumeration<String> es = arg0.getInitParameterNames();
			while (es.hasMoreElements()) {
				String name = es.nextElement();
				LogUtil.println(name + " : " + arg0.getInitParameter(name));
			}
		}

	}

	@Bean
	public RemoteIpFilter remoteIpFilter() {
		return new RemoteIpFilter();
	}

	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean() {
		FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
		frb.setFilter(new MyFilter());
		frb.addUrlPatterns("/*");
		frb.addInitParameter("paramName", "paramValue");
		frb.setName("MyFilter");
		frb.setOrder(1);
		return frb;
	}
}
