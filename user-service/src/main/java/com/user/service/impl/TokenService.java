package com.user.service.impl;

import com.user.service.ITokenService;
import com.user.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * token服务
 *
 * @author dingzi
 */
@Service
@Profile("dev")
public class TokenService implements ITokenService {

    private static final Logger LOG = LoggerFactory.getLogger(TokenService.class);

    private static final String REDIS_PREFIX_TOKEN = "idempotent_token_prefix";

    private static final String TOKEN_NAME = "idempotent_token";

    /** 失效时间*/
    private static final int EXPIRE_SECONDS = 5 * 60;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String createToken() {
        LOG.info("create token dev");
        String randomUuid = UUID.randomUUID().toString().replace("-", "");
        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(REDIS_PREFIX_TOKEN).append(randomUuid);
        try {
            redisUtil.setEx(tokenBuilder.toString(), tokenBuilder.toString(), EXPIRE_SECONDS);
            return tokenBuilder.toString();
        } catch (Exception e) {
            LOG.error("set redis fail", e);
        }
        return null;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        // 从header中获取token
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            // header中获取token失败，则从请求参数中获取
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {
                // token不存在，直接失败返回，不执行业务操作
                throw new IllegalArgumentException("token非法");
            }
        }
        boolean success = false;
        try {
            success = redisUtil.remove(token);
        } catch (Exception e) {
            LOG.error("del redis fail, key:{}", token, e);
        }
        if (!success) {
            throw new IllegalArgumentException("重复提交");
        }
        return true;
    }
}
