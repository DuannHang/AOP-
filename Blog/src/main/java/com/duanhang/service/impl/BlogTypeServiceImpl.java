package com.duanhang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duanhang.dao.BlogTypeDao;
import com.duanhang.entity.BlogType;
import com.duanhang.service.BlogTypeService;

/**
 * ��������Serviceʵ����
 * @author Administrator
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

	@Resource
	private BlogTypeDao blogTypeDao;
	
	public List<BlogType> countList() {
		return blogTypeDao.countList();
	}

	
}
