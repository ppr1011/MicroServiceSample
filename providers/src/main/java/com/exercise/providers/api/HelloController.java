package com.exercise.providers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.providers.dto.User;

import org.slf4j.LoggerFactory;

import java.util.Random;

import org.slf4j.Logger;

@RestController
public class HelloController {

	private final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String index() throws InterruptedException {		
		ServiceInstance instance = client.getLocalServiceInstance();
		int sleepTime = new Random().nextInt(2000);
		logger.info("sleepTime:" + sleepTime);
		Thread.sleep(sleepTime);		
		logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello World";
	}
	
	@RequestMapping(value="/hello1",method=RequestMethod.GET)
	public String hello(@RequestParam String name){
		return "Hello " + name; 
	}
	@RequestMapping(value="/hello2",method=RequestMethod.GET)
	public User hello(@RequestHeader String name,@RequestHeader Integer age) {
		return new User(name,age);
	}
	@RequestMapping(value="/hello3",method=RequestMethod.POST)
	public String hello(@RequestBody User user) {
		return "Hello "+ user.getName() + ", " + user.getAge();
	}

}
