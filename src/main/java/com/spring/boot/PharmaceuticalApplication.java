package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = "com.spring")
@EntityScan( basePackages = {"com.spring"} )
@EnableJpaRepositories("com.spring")
public class PharmaceuticalApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PharmaceuticalApplication.class, args);
	}
		
	 @Bean
	    public WebMvcConfigurer webMvcConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addResourceHandlers(ResourceHandlerRegistry registry) {
	                registry.addResourceHandler("/image/**")
	                        .addResourceLocations("file:src/main/resources/static/image/");
	            }
	        };
	    }

}
