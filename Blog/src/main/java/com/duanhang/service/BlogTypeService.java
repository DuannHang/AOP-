package com.duanhang.service;

import java.util.HashMap;
import java.util.List;

import com.duanhang.entity.BlogType;

/**
 * ��������Service�ӿ�
 * 
 * @author Administrator
 *
 */
public interface BlogTypeService {

	/**
	 * ��ѯ���в������ͣ��Լ���Ӧ�Ĳ�������
	 * 
	 * @return
	 */
	public List<BlogType> countList();

	public List<BlogType> list(HashMap<String, Object> map);

	public long getTotal(HashMap<String, Object> map);

	/**
	 * ��Ӳ��������Ϣ
	 * 
	 * @param blogType
	 * @return
	 */
	public Integer add(BlogType blogType);

	/**
	 * �޸Ĳ��������Ϣ
	 * 
	 * @param blogType
	 * @return
	 */
	public Integer update(BlogType blogType);

	/**
	 * ɾ�����������Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
