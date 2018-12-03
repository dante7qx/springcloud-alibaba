package org.dante.springcloud.alibaba.po;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
//@Proxy(lazy = false)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String account;
	private String name;
	private int age;
	private BigDecimal balance;

	public User() {
	}
	
	public User(Long id, String account) {
		this.id = id;
		this.account = account;
	}

}
