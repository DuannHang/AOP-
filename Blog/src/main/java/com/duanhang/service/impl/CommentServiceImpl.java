package com.duanhang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duanhang.dao.CommentDao;
import com.duanhang.entity.Comment;
import com.duanhang.service.CommentService;
@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Resource
	private	CommentDao commentDao;
	/**
	 *获取博客下面的评论
	 */
	public List<Comment> list(Map<String, Object> map) {
		return commentDao.list(map);
	}
	public int add(Comment comment) {
		return commentDao.add(comment);
	}

}
