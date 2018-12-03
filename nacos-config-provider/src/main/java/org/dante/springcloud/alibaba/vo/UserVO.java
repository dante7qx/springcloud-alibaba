package org.dante.springcloud.alibaba.vo;

import java.util.List;

public class UserVO<T> {
	private List<T> datas;

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

}
