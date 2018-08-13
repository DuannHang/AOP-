package com.duanhang.controller.admin;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duanhang.entity.BlogType;
import com.duanhang.entity.PageBean;
import com.duanhang.service.BlogService;
import com.duanhang.service.BlogTypeService;
import com.duanhang.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {
	@Resource
	private BlogTypeService blogTypeService;
	@Resource
	private BlogService blogService;

	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<BlogType> blogTypeList = blogTypeService.list(map);
		long total = blogTypeService.getTotal(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;

	}

	/**
	 * 添加博客类别、修改博客类别
	 * 
	 * @param blogType
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(BlogType blogType, HttpServletResponse response) throws Exception {
		int resultTotal = 0;
		if (blogType.getId() == null) {
			// 添加博客，这时候blogtypeid为null
			resultTotal = blogTypeService.add(blogType);
		} else {
			// 修改博客，blogtypeid不为null
			resultTotal = blogTypeService.update(blogType);
		}
		JSONObject result = new JSONObject();
		if (resultTotal > 0) {
			result.put("success", true);
		} else {
			result.put("success", false);
		}

		ResponseUtil.write(response, result);
		return null;
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response) throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for(int i=0;i<idsStr.length;i++) {
			if (blogService.getBlogByTypeId(Integer.parseInt(idsStr[i]))>0) {
				result.put("exist", "该类型下还有博客，不能删除！");
			}else {
				blogTypeService.delete(Integer.parseInt(idsStr[i]));
			}
		}
		
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
