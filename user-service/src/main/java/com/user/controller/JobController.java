package com.user.controller;

import com.user.annotation.AutoIdempotent;
import com.user.dto.ApiResult;
import com.user.dto.RpcResult;
import com.user.model.JobMapping;
import com.user.service.IJobMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @Autowired
    private IJobMappingService jobMappingService;

    @AutoIdempotent
    @PostMapping(value = "/job-mapping/add")
    public ApiResult add(JobMapping jobMapping) {
        RpcResult<Long> insert = jobMappingService.insert(jobMapping);
        return ApiResult.success(insert.getData());
    }

}
