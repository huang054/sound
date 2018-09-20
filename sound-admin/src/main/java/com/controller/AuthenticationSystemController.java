package com.controller;

import com.basic.constant.RedisConstant;
import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.mapper.AdminMapper;
import com.security.jwt.JWTConfigurer;
import com.security.jwt.TokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;


/**
 * @author music
 * @date 2017/8/24
 */
@RestController
@RequestMapping("/api/system")
@Api(description = "系统后台登陆接口 ")
public class AuthenticationSystemController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private AdminMapper adminMapper;

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    /**
     * 验证用户账号密码--登录(系统后台)
     *
     * @param userName
     * @param passWord
     * @param response
     * @return 登录成功，返回JWT Token
     * 登录失败，返回401
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ApiOperation("系统登录接口(非第三方)")
    public ResponseWrap<String> authorize(@ApiParam(value = "用户名") @RequestParam(required = false) String userName,
                                          @ApiParam(value = "密码") @RequestParam(required = false) String passWord,
                                          HttpServletResponse response) {

        if (userName == null || "".equals(userName)) {
            return new ResponseWrap<>(ResponseCode.CODE_1001, "用户名不能为空");
        }

        if (passWord == null || "".equals(passWord)) {
            return new ResponseWrap<>(ResponseCode.CODE_1001, "密码不能为空");
        }

        //加入前缀
        String username = StaticConstant.SYS_LOGIN_PREFIX + userName.trim();

        if (adminMapper.selectAuthByUsername(username) == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return new ResponseWrap<>(ResponseCode.CODE_1101);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, passWord.trim());

        try {
            //校验认证请求
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

            //封装登录信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //默认带rememberMe特性
            String jwt = tokenProvider.createToken(authentication, true);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);

            //成功登录，将token存储到redis
            redisTemplate.opsForHash().put(RedisConstant.SYSTEM_LOGIN_TOKEN, username, jwt);

            return new ResponseWrap<>(ResponseCode.CODE_200, jwt);
        } catch (AuthenticationException exception) {
            exception.printStackTrace();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return new ResponseWrap<>(ResponseCode.CODE_1009);
        }
    }

}
