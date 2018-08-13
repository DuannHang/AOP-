package com.duanhang.dao;

import java.util.List;
import java.util.Map;

import com.duanhang.entity.Comment;

/**
 * 博客评论
 * 
 * @author duanhang
 *
 */
public interface CommentDao {
	/**
	 * 查询博客的评论信息
	 * 
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String, Object> map);

	/**
	 * 添加评论
	 * 
	 * @param comment
	 * @return
	 */
	public int add(Comment comment);

	/**
	 * 获取总记录数
	 * 
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String, Object> map);

	/**
	 * 修改评论
	 * 
	 * @param comment
	 * @return
	 */
	public int update(Comment comment);

	/**
	 * 删除博客类别信息
	 * 
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
