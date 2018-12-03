package org.dante.springcloud.alibaba.controller;

import org.dante.springcloud.alibaba.prop.SpiritProperties;
import org.dante.springcloud.alibaba.service.DynaConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynaConfigController {

	@Autowired
	private SpiritProperties spiritProperties;
	@Autowired
	private DynaConfigService dynaConfigService;
	
	@GetMapping("/config1")
	public String loadConfig1() {
		return spiritProperties.toString();
	}
	
	@GetMapping("/config2")
	public String loadConfig2() {
		return dynaConfigService.getConfig();
	}
	
}
