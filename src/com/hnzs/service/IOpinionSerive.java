package com.hnzs.service;

import com.hnzs.service.bean.PageBean;

/**
 * 意见管理接口
 * 
 * @author Akina
 *
 */
public interface IOpinionSerive {

	/****
	 * 分页查询意见
	 * 
	 * @param page
	 * @return
	 */
	public PageBean findOpinByPage(Integer page);

	/*****
	 * 删除意见
	 * 
	 * @param opin_id
	 */
	public void delOpin(Integer opin_id);

}
