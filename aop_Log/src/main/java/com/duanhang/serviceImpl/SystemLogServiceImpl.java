package com.duanhang.serviceImpl;

import org.springframework.stereotype.Service;

import com.duanhang.dao.SystemLogMapper;
import com.duanhang.entity.SystemLog;
import com.duanhang.service.SystemLogService;
import javax.annotation.Resource;
@Service("SystemLogService")
public class SystemLogServiceImpl implements SystemLogService {
	  @Resource
	    private SystemLogMapper systemLogMapper;
	    
	    public int deleteSystemLog(String id) {
	        
	        return systemLogMapper.deleteByPrimaryKey(id);
	    }

	    public int insert(SystemLog record) {
	        
	        return systemLogMapper.insertSelective(record);
	    }

	    public SystemLog selectSystemLog(String id) {
	        
	        return systemLogMapper.selectByPrimaryKey(id);
	    }

	    public int updateSystemLog(SystemLog record) {
	        
	        return systemLogMapper.updateByPrimaryKeySelective(record);
	    }

	    public int insertTest(SystemLog record) {
	        
	        return systemLogMapper.insert(record);
	    }

}
