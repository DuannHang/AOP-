package com.duanhang.controller;

import java.util.Arrays;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.duanhang.entity.Blog;
import com.duanhang.entity.Blogger;
import com.duanhang.service.BlogService;
import com.duanhang.service.BloggerService;
import com.duanhang.service.CommentService;
import com.duanhang.util.CryptographyUtil;
import com.duanhang.util.StringUtil;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

/**
 * 博主Controller层
 * @author Administrator
 * @param <V>
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController<V> {

	@Resource
	private BlogService blogService;
	@Resource
	private CommentService commentService;
	
	@RequestMapping("/articles/{id}")
	public ModelAndView details(@PathVariable("id")Integer id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Blog blog = blogService.findById(id);
		String keyWord = blog.getKeyWord();
//		放博客关键字
		if (StringUtil.isNotEmpty(keyWord)) {
			String[] arr = keyWord.split(" ");
			mav.addObject("keyWord", StringUtil.filterWhite(Arrays.asList(arr)));
		}else {
			mav.addObject("keyWord", null);
		}
		
 		mav.addObject("blog", blog);
 		blog.setClickHit(blog.getClickHit()+1);
 		blogService.update(blog);
 		HashMap<String, Object> map = new HashMap<String, Object>();
 		map.put("blogId", blog.getId());
 		map.put("state",1);//设置为审核的，暂时的设计
 		mav.addObject("commentList", commentService.list(map));
 		
 		mav.addObject("pageCode", 
 				this.getUpAndDownPageCode(blogService.getLastBlog(id), 
 										  blogService.getNextBlog(id), 
 										  request.getServletContext().getContextPath()));
 		
		mav.addObject("pageTitle", blog.getTitle());
		mav.addObject("mainPage", "foreground/blog/view.jsp");
		mav.setViewName("mainTemp");
		return mav;
	}
	/**
	 * 获取博客的上下一篇文章
	 * @param lastBlog
	 * @param nextBlog
	 * @param projectContext
	 * @return
	 */
	public String getUpAndDownPageCode(Blog lastBlog,Blog nextBlog,String projectContext) {
		StringBuffer pageCode = new StringBuffer();
		if (lastBlog==null || lastBlog.getId()==null) {
			pageCode.append("<p>上一篇：没有了</p>");
		}else {
			pageCode.append("<p>上一篇：<a href='"+projectContext+"/blog/articles/"+lastBlog.getId()+".html'>"+lastBlog.getTitle()+"</a></p>");	
		}
		if (nextBlog==null || nextBlog.getId()==null) {
			pageCode.append("<p>下一篇：没有了</p>");
		}else {
			pageCode.append("<p>下一篇：<a href='"+projectContext+"/blog/articles/"+nextBlog.getId()+".html'>"+nextBlog.getTitle()+"</a></p>");	
		}
		return pageCode.toString();
		
	}
	
	
	
}
