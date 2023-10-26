package com.javademo.demohouseloan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title ="First Test hello APi", version="1.0", description="testing spring boot with swagger"))
public class DemoHouseLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHouseLoanApplication.class, args);
	}

}
