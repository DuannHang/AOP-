package com.duanhang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duanhang.dao.LinkDao;
import com.duanhang.entity.Link;
import com.duanhang.service.LinkService;

/**
 * ��������Serviceʵ����
 * @author Administrator
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkDao linkDao;

	public List<Link> list(Map<String, Object> map) {
		return linkDao.list(map);
	}

	
	
	

}
