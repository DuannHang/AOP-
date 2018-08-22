package com.duanhang.dao;

import org.springframework.stereotype.Repository;

import com.duanhang.entity.SystemLog;
@Repository
public interface SystemLogMapper {
	int deleteByPrimaryKey(String id);

	int insert(SystemLog record);

	int insertSelective(SystemLog record);

	SystemLog selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SystemLog record);

	int updateByPrimaryKey(SystemLog record);
}
