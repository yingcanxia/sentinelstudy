package cn.shadow.sentinel.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.sentinel.api.SentinelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Reference
	private SentinelService sentinelService;
	
	@GetMapping("/say")
	public String sayHello() {
		RpcContext.getContext().setAttachment("dubboApplication","sentinel-web");
		return sentinelService.sayHello("test");
	}
}
