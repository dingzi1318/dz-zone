package com.user.controller;


import com.user.dto.ApiResult;
import com.user.validator.constraint.NumberValidator;
import com.user.validator.group.AddGroup;
import com.user.validator.model.Topic;
import com.user.validator.util.TopicValidateUtil;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;


@RestController
public class TopicController {

    @PostMapping(value = "/topic/add")
    public Object add(Topic topic) {

        ApiResult apiResult = TopicValidateUtil.validateBean(topic, AddGroup.class, Default.class);

        return apiResult;
    }

    @GetMapping("/topic/get")
    public Object get(@NotNull(message = "用户ID不能为空")
                      @RequestParam(value = "userId") Long userId) {
        return userId;
    }

}
