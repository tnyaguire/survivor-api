package com.tnyagwaya.survivors.robot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.tnyagwaya.survivors")
public class ConfigProperties {
    private String robotCpuUrl;
    private int minFlags;
}
