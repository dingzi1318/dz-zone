package com.user.service.impl;

import com.user.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
@Profile("prod")
public class ProdTokenServiceImpl implements ITokenService {

    @Override
    public String createToken() {
        log.info("create token prod");
        return null;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        return false;
    }
}
