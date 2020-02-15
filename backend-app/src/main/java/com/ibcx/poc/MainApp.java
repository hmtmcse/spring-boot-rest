package com.ibcx.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sun.awt.AppContext;

@SpringBootApplication
public class MainApp implements WebMvcConfigurer {
	@Autowired
	private Environment environment;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
				.addResourceLocations(environment.getProperty("static.resource.path"));
	}
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
