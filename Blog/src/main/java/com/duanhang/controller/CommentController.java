package com.duanhang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duanhang.entity.Blog;
import com.duanhang.entity.Comment;
import com.duanhang.service.BlogService;
import com.duanhang.service.CommentService;
import com.duanhang.util.ResponseUtil;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Resource
	private BlogService blogService;
	@Resource
	private CommentService commentService;

	@RequestMapping("/save")
	public String save(Comment comment, @RequestParam("imageCode") String imageCode, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String sRand = (String) session.getAttribute("sRand");
		JSONObject result = new JSONObject();
		int resultTotal = 0;
		if (!imageCode.equals(sRand)) {
			result.put("success", false);
			result.put("errorInfo", "验证码错误啦！");
		} else {
			String userIp = request.getRemoteAddr();// 获取用户IP
			comment.setUserIp(userIp);
			if (comment.getId() == null) {
				// 添加评论
				resultTotal = commentService.add(comment);
				// 增加评论次数+1
				Blog blog = blogService.findById(comment.getBlog().getId());
				blog.setReplyHit(blog.getReplyHit() + 1);
				blogService.update(blog);
			}
		}
		if (resultTotal>0) {
			result.put("success", true);	
		}else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;

	}

}
