package com.security;

import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.utils.ResponseJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns a 401 error code (Unauthorized) to the client.
 */
@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(Http401UnauthorizedEntryPoint.class);

    /**
     * Always returns a 401 error code to the client.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) {

        log.debug("用户未提供Authentication在Header中，表明未登录");
        ResponseJsonUtils.sendMsgWithServletResponse(response, HttpServletResponse.SC_UNAUTHORIZED, new ResponseWrap<>(ResponseCode.CODE_1004));
    }
}
