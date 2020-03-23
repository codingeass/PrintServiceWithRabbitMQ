package com.report.printService;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(Sink.class)
@EnableRabbit
@Slf4j
public class PrintServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrintServiceApplication.class, args);
	}
	
	@StreamListener(Sink.INPUT)
	public void loggerSink(String message) {
		log.info("Message to be printed : {}", message);
	}

}
