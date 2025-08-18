package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // এখানে /** মানে সব endpoint-এর জন্য CORS apply হবে।
                registry.addMapping("/**")
                        .allowedOrigins("*") // আপনি চাইলে নির্দিষ্ট origin লিখতে পারেন।
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
               
            }
        };
    }


}
