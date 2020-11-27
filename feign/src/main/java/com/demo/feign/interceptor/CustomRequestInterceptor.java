package com.demo.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class CustomRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.err.println(requestTemplate.url());
        requestTemplate.header("myHeader", "xx.com");
    }
}
