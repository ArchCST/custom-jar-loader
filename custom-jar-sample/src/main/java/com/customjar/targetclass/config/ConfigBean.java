package com.customjar.targetclass.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ArchCST
 * 2023/2/10
 */
@Data
@Component
@ConfigurationProperties(prefix = ConfigBean.PREFIX)
public class ConfigBean {
    public static final String PREFIX = "extension.configs";

    private String someProperty;
}
