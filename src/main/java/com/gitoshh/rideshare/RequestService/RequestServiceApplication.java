package com.gitoshh.rideshare.RequestService;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableRabbit
@SpringBootApplication
public class RequestServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RequestServiceApplication.class, args);
	}

}
