package org.dante.springcloud.alibaba.service;

import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;

@Service
public class DegradeFallbackService {

	/**
	 * 仅针对降级功能生效（DegradeException）。fallback 函数的访问范围需要是 public，
	 * 参数类型和返回类型都需要与原方法相匹配，并且需要和原方法在同一个类中。
	 * 
	 * 若 blockHandler 和 fallback 都进行了配置，则遇到降级的时候首先选择 fallback 函数进行处理。
	 * 
	 * @param str
	 * @return
	 * @throws DegradeException
	 */
	@SentinelResource(value="Greet", fallback="greetFallback")
	public String greet(String str) throws DegradeException {
		if("fall".equals(str)) {
			throw new DegradeException(str);
		}
		return "Greet to " + str;
	}
	
	public String greetFallback(String str) {
		return "请求参数[ " + str + " ]，触发降级熔断";
	}
	
}
