package org.dante.springcloud.alibaba.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "spirit")
public class SpiritProperties {
	private String name;
	private String version;
	private String avatarUrl;
}
