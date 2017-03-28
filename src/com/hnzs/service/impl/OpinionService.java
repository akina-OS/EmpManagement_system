package com.hnzs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hnzs.dao.OpinionMapper;
import com.hnzs.service.IOpinionSerive;
import com.hnzs.service.bean.PageBean;
import com.hnzs.util.StaticStr;

/***
 * 意见管理实现
 * 
 * @author Akina
 *
 */
@Service("opinService")
public class OpinionService implements IOpinionSerive {

	@Resource
	private OpinionMapper opinMapping;

	@Override
	public PageBean findOpinByPage(Integer pageNum) {
		PageBean page = new PageBean();
		page.setDataList(opinMapping.findOPinDate((pageNum - 1) * StaticStr.PAGE_SIZE, StaticStr.PAGE_SIZE));
		// 查询一共多少条记录
		Integer count = opinMapping.findOpinCount();
		page.setCount(count);
		// 计算页数
		if (count % StaticStr.PAGE_SIZE == 0) {
			page.setTotalPage(count / StaticStr.PAGE_SIZE);
		} else {
			page.setTotalPage(count / StaticStr.PAGE_SIZE + 1);
		}
		return page;
	}

	@Override
	public void delOpin(Integer opin_id) {
		opinMapping.deleteByPrimaryKey(opin_id);
	}

}
