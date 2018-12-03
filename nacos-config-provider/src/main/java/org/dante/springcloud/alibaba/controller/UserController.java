package org.dante.springcloud.alibaba.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.dante.springcloud.alibaba.dao.UserDao;
import org.dante.springcloud.alibaba.po.User;
import org.dante.springcloud.alibaba.vo.PageReq;
import org.dante.springcloud.alibaba.vo.UserRespDTO;
import org.dante.springcloud.alibaba.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/healthz")
	public String healthz() {
		log.info("{} 开始健康检查", LocalDateTime.now());
		return "UP";
	}
	
	@Transactional
	@GetMapping("/user/{id}")
	public Mono<UserRespDTO> getUser(@PathVariable Long id) {
		UserRespDTO userResp = new UserRespDTO();
		User user = userDao.findById(id).get();
		BeanUtils.copyProperties(user, userResp);
		return Mono.just(userResp);
	}
	
	@GetMapping("/user/all")
	public Flux<User> getAllUser() {
		return Flux.just(userDao.findAll(new Sort(Direction.DESC, "id")).toArray(new User[0]));
	}
	
	@PostMapping("/user/{id}")
	public Mono<UserRespDTO> getUser(@PathVariable Long id, @RequestBody User usr) {
		UserRespDTO user = new UserRespDTO();
		user.setId(id);
		user.setAccount(usr.getAccount());
		user.setName(usr.getName());
		return Mono.just(user);
	}
	
	@PostMapping("/user_id")
	public Mono<UserRespDTO> getUserId(@RequestParam("id") Long id) {
		UserRespDTO user = new UserRespDTO();
		user.setId(id);
		return Mono.just(user);
	}
	
	@PostMapping("/user-list")
	public Mono<List<User>> getUserList(@RequestBody User user) {
		List<User> list = Lists.newArrayList();
		for (int i = 1; i <= 3; i++) {
			list.add(new User(user.getId(), user.getAccount()+"_"+i));
		}
		return Mono.just(list);
	}
	
	@PostMapping("/user-vos")
	public Mono<UserVO<User>> getUserVoList(@RequestBody PageReq pageReq) {
		UserVO<User> vo = new UserVO<User>();
		List<User> list = Lists.newArrayList();
		for (int i = 1; i <= 3; i++) {
			list.add(new User(Long.valueOf(i), "user_"+i));
		}
		vo.setDatas(list);
		return Mono.just(vo);
	}
	
	@PutMapping("/add-user")
	public Flux<User> addUser(@RequestBody User user) {
		userDao.save(user);
		return Flux.just(userDao.findAll(new Sort(Direction.DESC, "id")).toArray(new User[0]));
	}
	
	@DeleteMapping("/del-user/{id}")
	public Flux<User> delUser(@PathVariable Long id) {
		userDao.deleteById(id);
		return Flux.just(userDao.findAll(new Sort(Direction.DESC, "id")).toArray(new User[0]));
	}
	
}
