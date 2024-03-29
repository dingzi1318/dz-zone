package com.user.validator;

import com.alibaba.fastjson.JSON;
import com.user.dto.ApiResult;
import com.user.validator.group.UpdateGroup;
import com.user.validator.model.MyAccount;
import com.user.validator.model.Topic;
import com.user.validator.util.TopicValidateUtil;
import com.user.validator.util.ValidationUtil;
import org.junit.Test;


/**
 * @Description TODO
 * @Author dingzi
 * @Date 2020/5/22 11:17
 */
public class ValidationTest {

    @Test
    public void test5() {
        MyAccount account = new MyAccount();
        account.setAlias("kalakala");
        account.setPassWord("密码");
        account.setBirthday("2019.12.1");
        account.setLevel(null);
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(account);
        if (validResult.hasErrors()) {
            String properties = validResult.getProperties();
            System.out.println(properties);
        }
    }

    @Test
    public void test6() {
        MyAccount account = new MyAccount();
        account.setAlias("kalakala");
        account.setUserName("wokalakala");
        account.setPassWord(null);
        account.setBirthday("2020-10-02");
        account.setLevel(null);
        // 指定分组 AccountService.class
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(account);
        if (validResult.hasErrors()) {
            String errors = validResult.getErrors();
            String properties = validResult.getProperties();
            System.out.println(errors);
            System.out.println(properties);
        }
    }

    @Test
    public void topicSubmitTest() {
        Topic param = new Topic();
        param.setId(null);
        ApiResult feJsonResult = TopicValidateUtil.validateBean(param, UpdateGroup.class);
        System.out.println(JSON.toJSONString(feJsonResult));
    }
}
