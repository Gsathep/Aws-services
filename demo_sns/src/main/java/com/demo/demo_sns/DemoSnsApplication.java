package com.demo.demo_sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class DemoSnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSnsApplication.class, args);
	}

}
