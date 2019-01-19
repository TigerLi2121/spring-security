package com.mm.web.controller;

import com.mm.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lwl
 */
@Slf4j
@RestController
public class WebSecurityController {

    private RequestCache cache = new HttpSessionRequestCache();

    private RedirectStrategy strategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/auth/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String requireAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = cache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求路径是:{}", targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                strategy.sendRedirect(request, response, securityProperties.getWeb().getLoginPage());
            }
        }
        return "访问的服务需要省份验证， 请引导用户到登录页";
    }
}
