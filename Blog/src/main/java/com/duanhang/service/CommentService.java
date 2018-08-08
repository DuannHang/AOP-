package com.duanhang.service;

import java.util.List;
import java.util.Map;

import com.duanhang.entity.Comment;

public interface CommentService {
	/**
	 * 查询用户评论信息
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String,Object> map);
	public int add(Comment comment);
}
