package com.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("some.service")
public class SomeServiceProperties {

    private String before;

    private String after;

}
