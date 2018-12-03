package org.dante.springcloud.alibaba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

import reactor.core.publisher.Mono;

@Service
public class ConfigService {
	@Value("${spirit.provider.name}")
	private String appName;
	@Autowired
	private LoadBalancerExchangeFilterFunction lbFunction;
	@Autowired
	private RestTemplate restTemplate;
	
	@SentinelResource(value = "config", fallback="fallback1")
	public Mono<String> getProviderConfig(Integer inc) {
		return WebClient.builder().baseUrl("http://".concat(appName)).filter(lbFunction).build().get()
				.uri("/config".concat(inc + "")).retrieve().bodyToMono(String.class);
//		return restTemplate.getForObject("http://".concat(appName).concat("/config" + inc), String.class);
	}
	
	public Mono<String> fallback(Integer inc) {
		return Mono.just("熔断降级处理..." + inc);
	}
	
//	@SentinelResource(value = "config1", blockHandler="handleException", blockHandlerClass= {ExceptionUtil.class})
	public String getProviderConfig1(Integer inc) {
		return restTemplate.getForObject("http://".concat(appName).concat("/config" + inc), String.class);
	}
	
	public String fallback1(Integer inc) {
		return "熔断降级处理..." + inc;
	}
	
}
