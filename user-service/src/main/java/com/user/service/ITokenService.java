package com.daojia.web.workerconversion.idempotent.service;

import javax.servlet.http.HttpServletRequest;

/**
 * token服务
 *
 */
public interface ITokenService {

    /**
     * 创建token
     *
     * @return token
     */
    String createToken();

    /**
     * 校验token
     *
     * @param request
     * @return token是否合法
     */
    boolean checkToken(HttpServletRequest request);

}
