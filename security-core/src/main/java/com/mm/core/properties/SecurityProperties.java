package com.mm.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lwl
 */
@ConfigurationProperties(prefix = "auth.security")
public class SecurityProperties {

    private WebProperties web = new WebProperties();

    public WebProperties getWeb() {
        return web;
    }

    public void setWeb(WebProperties web) {
        this.web = web;
    }
}
