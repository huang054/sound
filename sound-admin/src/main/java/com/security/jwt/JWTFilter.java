package com.security.jwt;

import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.utils.ResponseJsonUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid UserController is
 * found.
 *
 * @author music
 */
@Component
public class JWTFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(JWTFilter.class);

    private TokenProvider tokenProvider;

    public JWTFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String jwt = resolveToken(httpServletRequest);

            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            if (StringUtils.hasText(jwt)) {
                if (this.tokenProvider.validateToken(jwt)) {
                    Authentication authentication = this.tokenProvider.getAuthentication(jwt);

                    String userAccount = authentication.getName();

                    //查看账户是否冻结
                    if (tokenProvider.isFreeze(userAccount)) {
                        ResponseJsonUtils.sendMsgWithServletResponse(servletResponse, HttpServletResponse.SC_UNAUTHORIZED, new ResponseWrap<>(ResponseCode.CODE_1018));
                        return;
                    }

                    String redisAppToken = tokenProvider.getRedisAppToken(userAccount);

                    //token与redis中不一致，不予许登陆
                    if (!jwt.equals(redisAppToken)) {
                        ResponseJsonUtils.sendMsgWithServletResponse(servletResponse, HttpServletResponse.SC_UNAUTHORIZED, new ResponseWrap<>(ResponseCode.CODE_1005));
                        return;
                    }
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ExpiredJwtException eje) {
            log.info("Token认证失败，Token信息为： {} - {}", eje.getClaims().getSubject(), eje.getMessage());
            ResponseJsonUtils.sendMsgWithServletResponse(servletResponse, HttpServletResponse.SC_UNAUTHORIZED, new ResponseWrap<>(ResponseCode.CODE_1005));
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
        if (bearerToken == null || bearerToken.equals("")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        bearerToken = "Bearer " + cookie.getValue();
                        break;
                    }
                }
            }
        }
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String jwt = bearerToken.substring(7, bearerToken.length());
            return jwt;
        }
        String jwt = request.getParameter(JWTConfigurer.AUTHORIZATION_TOKEN);
        if (StringUtils.hasText(jwt)) {
            return jwt;
        }
        return null;
    }
}
