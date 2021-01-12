package com.user.service.impl;

import com.user.annotation.RpcServiceBehavior;
import com.user.dto.RpcResult;
import com.user.exception.RpcBusinessException;
import com.user.mapper.JobMappingMapper;
import com.user.model.JobMapping;
import com.user.service.IJobMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
//@RpcServiceBehavior
public class JobMappingService  implements IJobMappingService {

    @Autowired
    private JobMappingMapper jobMappingMapper;

    @Override
    public RpcResult<JobMapping> selectById(Long id) {
        if (id == null || id <= 0L) {
            throw new RpcBusinessException(-1, "ID参数非法");
        }
        JobMapping jobMapping = jobMappingMapper.selectByPrimaryKey(id);
        return RpcResult.success(jobMapping);
    }

    @Override
    public RpcResult<Long> insert(JobMapping jobMapping) {
        jobMapping.setCreateTime(new Date());
        int id = jobMappingMapper.insertSelective(jobMapping);
        return RpcResult.success(Long.valueOf(id));
    }
}
