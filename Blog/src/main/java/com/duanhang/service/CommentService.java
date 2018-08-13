package com.duanhang.service;

import java.util.List;
import java.util.Map;

import com.duanhang.entity.Comment;

public interface CommentService {
	/**
	 * ��ѯ�û�������Ϣ
	 * 
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String, Object> map);

	public int add(Comment comment);

	/**
	 * ��ȡ�ܼ�¼��
	 * 
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String, Object> map);

	/**
	 * �޸�����
	 * 
	 * @param comment
	 * @return
	 */
	public int update(Comment comment);

	/**
	 * ɾ�����������Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
