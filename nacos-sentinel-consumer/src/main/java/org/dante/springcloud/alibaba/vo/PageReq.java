package org.dante.springcloud.alibaba.vo;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

public class PageReq implements Serializable {

	private static final long serialVersionUID = -8240990382392401666L;
	
	private final static int DEFAULT_PAGENO = 1;
	private final static int DEFAULT_PAGESIZE = 20;
	private final static int MAX_PAGESIZE = 500;
	
	public final static String ASC = "asc";
	public final static String DESC = "desc";

	private int pageNo = DEFAULT_PAGENO;
	private int pageSize = DEFAULT_PAGESIZE;
	private String sort;
	private String order = ASC;

	private Map<String, Object> q;
	
	public PageReq() {
	}

	public void setPage(int page) {
		if(page > MAX_PAGESIZE) {
			page = MAX_PAGESIZE;
		} 
		this.pageNo = page;
	}

	public void setRows(int rows) {
		this.pageSize = rows;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Map<String, Object> getQ() {
		if(q == null) {
			q = Maps.newHashMap();
		}
		return q;
	}

	public void setQ(Map<String, Object> q) {
		this.q = q;
	}

	@Override
	public String toString() {
		return "PageReq [pageNo=" + pageNo + ", pageSize=" + pageSize + ", sort=" + sort + ", order=" + order + ", q="
				+ q + "]";
	}

	
}
