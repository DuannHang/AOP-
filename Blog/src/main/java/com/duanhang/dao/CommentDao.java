package com.duanhang.dao;

import java.util.List;
import java.util.Map;

import com.duanhang.entity.Comment;

/**
 * ��������
 * 
 * @author duanhang
 *
 */
public interface CommentDao {
	/**
	 * ��ѯ���͵�������Ϣ
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String,Object>map);
	public int add(Comment comment);
}
