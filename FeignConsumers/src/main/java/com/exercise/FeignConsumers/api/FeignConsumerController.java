package com.exercise.FeignConsumers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.FeignConsumers.dto.User;
import com.exercise.FeignConsumers.sao.HelloSao;

@RestController
public class FeignConsumerController {

	@Autowired
	HelloSao helloSao;
	
	@RequestMapping(value="/feign-consumer",method=RequestMethod.GET)
	public String helloConsumer() {
		return helloSao.hello();
	}
	
	@RequestMapping(value="/feign-consumer2",method=RequestMethod.GET)
	public String helloConsumer2() {
		StringBuilder sb = new StringBuilder();
		sb.append(helloSao.hello()).append('\n');
		sb.append(helloSao.hello("DIDI")).append('\n');
		sb.append(helloSao.hello("DIDI",30)).append('\n');
		sb.append(helloSao.hello(new User("DIDI",30))).append('\n');
		return sb.toString();
		
	}
	
}
