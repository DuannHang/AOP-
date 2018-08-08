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
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String,Object>map);
	public int add(Comment comment);
}
