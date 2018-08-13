package com.duanhang.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.duanhang.entity.Blog;
import com.duanhang.lucene.BlogIndex;
import com.duanhang.service.BlogService;
import com.duanhang.service.CommentService;
import com.duanhang.util.StringUtil;

/**
 * ����Controller��
 * 
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
	private BlogIndex blogIndex = new BlogIndex();

	@RequestMapping("/articles/{id}")
	public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Blog blog = blogService.findById(id);
		String keyWord = blog.getKeyWord();
		// �Ų��͹ؼ���
		if (StringUtil.isNotEmpty(keyWord)) {
			String[] arr = keyWord.split(" ");
			mav.addObject("keyWord", StringUtil.filterWhite(Arrays.asList(arr)));
		} else {
			mav.addObject("keyWord", null);
		}

		mav.addObject("blog", blog);
		blog.setClickHit(blog.getClickHit() + 1);
		blogService.update(blog);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("blogId", blog.getId());
		map.put("state", 1);// ����Ϊ��˵ģ���ʱ�����
		mav.addObject("commentList", commentService.list(map));

		mav.addObject("pageCode", this.getUpAndDownPageCode(blogService.getLastBlog(id), blogService.getNextBlog(id),
				request.getServletContext().getContextPath()));

		mav.addObject("pageTitle", blog.getTitle());
		mav.addObject("mainPage", "foreground/blog/view.jsp");
		mav.setViewName("mainTemp");
		return mav;
	}

	/**
	 * ��ȡ���͵�����һƪ����
	 * 
	 * @param lastBlog
	 * @param nextBlog
	 * @param projectContext
	 * @return
	 */
	public String getUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext) {
		StringBuffer pageCode = new StringBuffer();
		if (lastBlog == null || lastBlog.getId() == null) {
			pageCode.append("<p>��һƪ��û����</p>");
		} else {
			pageCode.append("<p>��һƪ��<a href='" + projectContext + "/blog/articles/" + lastBlog.getId() + ".html'>"
					+ lastBlog.getTitle() + "</a></p>");
		}
		if (nextBlog == null || nextBlog.getId() == null) {
			pageCode.append("<p>��һƪ��û����</p>");
		} else {
			pageCode.append("<p>��һƪ��<a href='" + projectContext + "/blog/articles/" + nextBlog.getId() + ".html'>"
					+ nextBlog.getTitle() + "</a></p>");
		}
		return pageCode.toString();

	}

	/**
	 * ���ݹؼ��ֲ�ѯ��ز�����Ϣ
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/q")
	public ModelAndView search(@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "page", required = false) String page, HttpServletRequest request) throws Exception {
		int pageSize = 2;// ÿһ�����������ҳ����������ÿһҳ2��
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "�����ؼ���'" + q + "'���ҳ��_java��Դ����ϵͳ");
		mav.addObject("mainPage", "foreground/blog/result.jsp");
		List<Blog> blogList = blogIndex.searchBlog(q);
		Integer toIndex = blogList.size() >= Integer.parseInt(page) * pageSize ? Integer.parseInt(page) * pageSize
				: blogList.size();
		List<Blog> subList = blogList.subList((Integer.parseInt(page) - 1) * pageSize, toIndex);
		mav.addObject("blogList",subList);
		mav.addObject("pageCode", this.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q, pageSize,
				request.getServletContext().getContextPath()));
		mav.addObject("q", q);
		mav.addObject("resultTotal", blogList.size());
		mav.setViewName("mainTemp");
		return mav;
	}

	/**
	 * ��ȡ��һҳ����һҳ����
	 * 
	 * @param page
	 * @param totalNum
	 * @param q
	 * @param pageSize
	 * @param projectContext
	 * @return
	 */
	private String genUpAndDownPageCode(Integer page, Integer totalNum, String q, Integer pageSize,
			String projectContext) {
		long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		if (totalPage == 0) {
			return "";
		} else {
			pageCode.append("<nav>");
			pageCode.append("<ul class='pager'>");
			if (page > 1) {
				pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page - 1) + "&q=" + q
						+ "'>��һҳ</a></li>");
			} else {
				pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
			}
			if (page < totalPage) {
				pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page + 1) + "&q=" + q
						+ "'>��һҳ</a></li>");
			} else {
				pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
			}
			pageCode.append("</ul>");
			pageCode.append("</nav>");
		}
		return pageCode.toString();
	}

}
