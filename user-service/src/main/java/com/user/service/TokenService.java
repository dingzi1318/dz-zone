package com.daojia.web.workerconversion.idempotent.service;

import com.daojia.web.workerconversion.utils.DjedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * token服务
 *
 * @author dingzi
 */
@Service
public class TokenService implements ITokenService {

    private static final Logger LOG = LoggerFactory.getLogger(TokenService.class);

    private static final String REDIS_PREFIX_TOKEN = "idempotent_token_prefix";

    private static final String TOKEN_NAME = "idempotent_token";

    /** 失效时间*/
    private static final int EXPIRE_SECONDS = 5 * 60;

    @Override
    public String createToken() {
        String randomUuid = UUID.randomUUID().toString().replace("-", "");
        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(REDIS_PREFIX_TOKEN).append(randomUuid);
        try {
            DjedisUtil.getDjedis().setex(tokenBuilder.toString(), EXPIRE_SECONDS, tokenBuilder.toString());
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
            Long result = DjedisUtil.getDjedis().del(token);
            success = result != null && result > 0L;
        } catch (Exception e) {
            LOG.error("del redis fail, key:{}", token, e);
        }

        if (!success) {
            throw new IllegalArgumentException("重复提交");
        }

        return false;
    }
}
