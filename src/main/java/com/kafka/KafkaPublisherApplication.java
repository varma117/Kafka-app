package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topic = "spring-kafka";
	
	@GetMapping("/publis/{name}")
	public String publishMessage(@PathVariable String name) {
		template.send(topic, "Hello" + name);
		return "data has been published";
		
	}
	@GetMapping("/publishjson")
	public String publishMessage() {
		User user = new User(123,"varma", new String[] {"andhra","westgodavari","palakollu"});
		template.send(topic,user);
		return "json data has published";
		
	}
	

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
