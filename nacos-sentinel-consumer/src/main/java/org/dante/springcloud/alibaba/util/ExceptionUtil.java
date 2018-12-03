package org.dante.springcloud.alibaba.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionUtil {
	public static String handleException(String str, BlockException ex) {
		log.error("请求参数 {} 发生问题 {}", str, ex.getMessage(), ex);
		String returnErr = Thread.currentThread().getName().concat(" ——> 处理请求参数").concat(str).concat("失败！");
        return returnErr;
	}
}
