package org.dante.springcloud.alibaba.controller;

import org.dante.springcloud.alibaba.service.LimitRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 限流测试
 * 
 * @author dante
 *
 */
@RestController
public class LimitRequestController {
		
	@Autowired
	private LimitRequestService limitRequestService;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello, 你好！";
	}
	
	@GetMapping("/test/{str}")
	public String test(@PathVariable String str) {
		return limitRequestService.testLimit(str);
	}
	
	@GetMapping("/test2/{str}")
	public String test2(@PathVariable String str) {
		return limitRequestService.testLimit2(str);
	}
	
}
