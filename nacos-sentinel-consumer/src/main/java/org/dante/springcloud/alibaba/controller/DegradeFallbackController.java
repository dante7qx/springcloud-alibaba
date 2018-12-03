package org.dante.springcloud.alibaba.controller;

import org.dante.springcloud.alibaba.service.DegradeFallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;

/**
 * 降级熔断测试
 * 
 * @author dante
 *
 */
@RestController
public class DegradeFallbackController {
	
	@Autowired
	private DegradeFallbackService degradeFallbackService;

	@GetMapping("/greet")
	public String greet(@RequestParam(name="str", defaultValue="但丁") String str) throws DegradeException {
		return degradeFallbackService.greet(str);
	}
	
}
