package com.jedrzejewski.genderdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GenderDetectorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GenderDetectorApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GenderDetectorApplication.class);
    }
}
