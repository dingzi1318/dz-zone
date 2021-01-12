package com.user.service;

import com.user.dto.RpcResult;
import com.user.model.JobMapping;

public interface IJobMappingService {

    RpcResult<JobMapping> selectById(Long id);

    RpcResult<Long> insert(JobMapping jobMapping);
}
