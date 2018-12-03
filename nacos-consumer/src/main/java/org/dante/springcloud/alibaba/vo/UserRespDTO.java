package org.dante.springcloud.alibaba.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UserRespDTO {
	private Long id;
	private String account;
	private String name;
	private int age;
	private BigDecimal balance;
}
