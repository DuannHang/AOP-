package com.duanhang.dao;

import java.util.HashMap;
import java.util.List;

import com.duanhang.entity.BlogType;

/**
 * 博客类型Dao接口
 * 
 * @author Administrator
 *
 */
public interface BlogTypeDao {

	/**
	 * 查询所有博客类型，以及对应的博客数量
	 * 
	 * @return
	 */
	public List<BlogType> countList();

	/**
	 * 通过id查找博客类型实体
	 * 
	 * @param id
	 * @return
	 */
	public BlogType findById(Integer id);

	/**
	 * 查询博客类别列表
	 * 
	 * @param map
	 * @return
	 */
	public List<BlogType> list(HashMap<String, Object> map);

	/**
	 * 查询博客类别的数量
	 * 
	 * @param map
	 * @return
	 */
	public long getTotal(HashMap<String, Object> map);

	/**
	 * 添加博客类别信息
	 * 
	 * @param blogType
	 * @return
	 */
	public Integer add(BlogType blogType);

	/**
	 * 修改博客类别信息
	 * 
	 * @param blogType
	 * @return
	 */
	public Integer update(BlogType blogType);

	/**
	 * 删除博客类别信息
	 * 
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
