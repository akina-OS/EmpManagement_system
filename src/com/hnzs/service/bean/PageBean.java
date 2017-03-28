package com.hnzs.service.bean;

import java.util.ArrayList;
import java.util.List;

/***
 * 分页工具实体
 * 
 * @author Akina
 *
 */
public class PageBean {

	/***
	 * 数据集合
	 */
	private List<?> dataList = new ArrayList<Object>();

	/***
	 * 记录总条数
	 */
	private int count;

	/**
	 * 总页数
	 */
	private int totalPage;

	@Override
	public String toString() {
		return "PageBean [dataList=" + dataList + ", count=" + count + ", totalPage=" + totalPage + "]";
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
