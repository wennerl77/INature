package com.ifmg.server.apiServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ApiServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApiServerApplication.class, args);
		System.out.println("Iniciando");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiServerApplication.class);
	}
}
