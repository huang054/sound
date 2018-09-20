package com.security;

import com.service.AdminManageService;
import com.vo.AccountAuthVO;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Authenticate a UserController from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private AdminManageService adminManageService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        if (login != null && login.indexOf("GZH_") == 0) {
            String userName = login.substring("GZH_".length());
            return weChatLogin(userName);
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        AccountAuthVO accountAuthVO = adminManageService.selectAuthByUsername(lowercaseLogin);
        //不存在直接抛出异常
        if (accountAuthVO == null) {
            log.warn("[" + lowercaseLogin + "] 不存在!");
            throw new UsernameNotFoundException(lowercaseLogin + " 不存在!");
        }
        //是否启用
        boolean enable = accountAuthVO.getStatus() == 1;
        //权限列表
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(accountAuthVO.getResourceName());
        //包装用户授权信息
        User userDetails = new User(accountAuthVO.getUserAccount(), accountAuthVO.getUserPasswd(),
                enable, true, true, true, grantedAuths);
        return userDetails;
    }

    /**
     * 将搜索的结果数据获取，并返回权限列表
     *
     * @param resourceNames
     * @return
     */
    private Collection<GrantedAuthority> obtionGrantedAuthorities(List<String> resourceNames) {
        Set<GrantedAuthority> authSet = new HashSet<>();
        if (resourceNames == null) {
            return authSet;
        }
        //遍历封装
        for (String accountAuthVO : resourceNames) {
            if (!StringUtil.isNullOrEmpty(accountAuthVO)) {
                authSet.add(new SimpleGrantedAuthority(accountAuthVO));
            }
        }
        return authSet;
    }

    private UserDetails weChatLogin(String userName) {
        String lowercaseLogin = userName.toLowerCase(Locale.ENGLISH);
        AccountAuthVO accountAuthVO = adminManageService.selectAuthByUsername(lowercaseLogin);
        //不存在直接抛出异常
        if (accountAuthVO == null) {
            log.warn("[" + lowercaseLogin + "] 不存在!");
            throw new UsernameNotFoundException(lowercaseLogin + " 不存在!");
        }
        //是否启用
        boolean enable = accountAuthVO.getStatus() == 1;
        //权限列表

        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(accountAuthVO.getResourceName());
        //包装用户授权信息
        User userDetails = new User(accountAuthVO.getUserAccount(), "$2a$10$m5FcgxOC9fBxBKCR4WD0gOYq29CsuJ0owXxmj4hrGpuEO3r/ldf0.",
                enable, true, true, true, grantedAuths);
        return userDetails;
    }
}
