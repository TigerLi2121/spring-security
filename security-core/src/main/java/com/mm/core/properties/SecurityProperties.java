package com.mm.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lwl
 */
@Data
@ConfigurationProperties(prefix = "auth.security")
public class SecurityProperties {

    private WebProperties web = new WebProperties();
}
