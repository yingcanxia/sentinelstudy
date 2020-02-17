package cn.shadow.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;


@RestController
public class SentinelController {
	
	// 设置限流的注解,针对方法级别的限流
	@SentinelResource(value="sayHello")
	@GetMapping("/say")
	public String sayHello() {
		 return "hello word";
	}
	
}
