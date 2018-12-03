package org.dante.springcloud.alibaba.service;

import org.dante.springcloud.alibaba.prop.SpiritProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynaConfigService {
	
	@Autowired
	private SpiritProperties spiritProperties;
	
	public String getConfig() {
		return spiritProperties.toString();
	}
	
}
