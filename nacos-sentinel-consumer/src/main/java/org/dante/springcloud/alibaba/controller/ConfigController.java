package org.dante.springcloud.alibaba.controller;

import org.dante.springcloud.alibaba.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class ConfigController {

	@Autowired
	private ConfigService configService;
	
	@GetMapping("/")
	public String home() {
		return "Sentinel: 分布式系统的流量防卫兵"; 
	}

	@GetMapping("/config/{inc}")
	public Mono<String> getProviderConfig(@PathVariable Integer inc) {
		return configService.getProviderConfig(inc);
	}
	
	@GetMapping("/config1/{inc}")
	public String getProviderConfig1(@PathVariable Integer inc) {
		return configService.getProviderConfig1(inc);
	}

	
}
