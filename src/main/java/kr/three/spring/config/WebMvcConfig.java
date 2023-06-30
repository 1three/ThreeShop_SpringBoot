package kr.three.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

	@Value(value="${uploagPath}")
	private String uploagPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")	// When all URLs start with /images/,
				.addResourceLocations(uploagPath);	// Set the resource to be found
	}
	
}
