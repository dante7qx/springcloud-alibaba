package org.dante.springcloud.alibaba.service;

import org.dante.springcloud.alibaba.util.ExceptionUtil;
import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import lombok.extern.slf4j.Slf4j;

/**
 * 1. blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException
 * 
 * 
 * @author dante
 *
 */
@Slf4j
@Service
public class LimitRequestService {
	
	/**
	 * 希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
	 * 
	 * @param str
	 * @return
	 */
	@SentinelResource(value="testReq", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
	public String testLimit(String str) {
		return "Test Limit1 请求参数 ——> " + str;
	}
	
	/**
	 * blockHandler 函数默认需要和原方法在同一个类中。 public String exceptionHandler(String str, BlockException ex) 
	 * 
	 * @param str
	 * @return
	 */
	@SentinelResource(value="testReq2", blockHandler="exceptionHandler")
	public String testLimit2(String str) {
		return "Test Limit2 请求参数 ——> " + str;
	}
	
	public String exceptionHandler(String str, BlockException ex) {
		log.error("Test Limit2 请求参数 {} 发生问题 {}", str, ex.getMessage(), ex);
		String returnErr = Thread.currentThread().getName().concat(" ——> 处理请求参数2").concat(str).concat("失败！");
        return returnErr;
    }
}
