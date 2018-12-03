package org.dante.springcloud.alibaba.controller;

import java.time.LocalDateTime;

import org.dante.springcloud.alibaba.vo.UserRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class UserController {
	
	@Value("${spirit.provider.name}")
    private String appName;

	@Autowired
	@LoadBalanced
	private WebClient.Builder webClientBuilder;
	@Autowired
	private LoadBalancerExchangeFilterFunction lbFunction;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/user/{id}")
	public Mono<UserRespDTO> getUser(@PathVariable Long id) {
		log.info("Request to req /user/id {}.", id);
		return webClientBuilder.build().get().uri("/user/".concat(id + "")).retrieve().bodyToMono(UserRespDTO.class);
	}

	@GetMapping("/userx/{id}")
	public Mono<UserRespDTO> getUserx(@PathVariable Long id) {
		log.info("Request to req /user/id {}.", id);
		return webClientBuilder.build().get().uri("/user/".concat(id + "")).retrieve().bodyToMono(UserRespDTO.class);
	}

	@GetMapping("/userc/{id}")
	public Mono<UserRespDTO> getUserc(@PathVariable Long id) {
		log.info("Request to req /user/id {}.", id);
		return WebClient.builder().baseUrl("http://nacos-provider").filter(lbFunction).build().get()
				.uri("/user/".concat(id + "")).retrieve().bodyToMono(UserRespDTO.class);
	}
	
	@GetMapping("/rlu/{id}")
	public UserRespDTO userRestTemolateAndLoadBalance(@PathVariable Long id) {
		//使用 LoadBalanceClient 和 RestTemolate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String url = String.format("http://%s:%s/user/%s",serviceInstance.getHost(),serviceInstance.getPort(), id);
        log.info("request url: {}", url);
        return restTemplate.getForObject(url, UserRespDTO.class);
	}

	@GetMapping("/healthz")
	public String healthz() {
		log.info("{} 开始健康检查", LocalDateTime.now());
		return "UP";
	}
}
