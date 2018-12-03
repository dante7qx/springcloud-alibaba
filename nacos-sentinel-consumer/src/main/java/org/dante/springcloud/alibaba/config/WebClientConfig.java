package org.dante.springcloud.alibaba.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
	@Value("${spirit.provider.name}")
    private String appName;
	
	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder().baseUrl("http://".concat(appName));
	}
	
	@Bean
//	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
