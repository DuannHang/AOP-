package com.duanhang.service;

import java.util.List;

import com.duanhang.entity.BlogType;

/**
 * ��������Service�ӿ�
 * @author Administrator
 *
 */
public interface BlogTypeService {

	/**
	 * ��ѯ���в������ͣ��Լ���Ӧ�Ĳ�������
	 * @return
	 */
	public List<BlogType> countList();
}
