package com.security.jwt;

import com.basic.constant.RedisConstant;
import com.dao.mapper.AdminMapper;
import com.dao.model.Admin;
import com.security.AuthoritiesConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author music
 */
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private String secretKey;

    private long tokenValidityInSeconds;

    private long tokenValidityInSecondsForRememberMe;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AdminMapper adminMapper;

    @PostConstruct
    public void init() {
        this.secretKey = "my-secret-token-to-change-in-production";
        this.tokenValidityInSeconds = 1000 * 60 * 60 * 24 * 10;
        this.tokenValidityInSecondsForRememberMe = 1000 * 60 * 60 * 24 * 10;
    }

    public String createToken(Authentication authentication, Boolean rememberMe) {
        //将权限设置进入Redis

        String authorities = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));
        String username = authentication.getName();
        redisTemplate.opsForHash().put(RedisConstant.SYS_AUTHORITY, username, authorities);
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInSecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInSeconds);
        }
        return Jwts.builder()
                .setSubject(authentication.getName())
                //设置为null
                .claim(AUTHORITIES_KEY, null)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        String name = claims.get("sub").toString();
        String roleRecs = redisTemplate.opsForHash().get(RedisConstant.SYS_AUTHORITY, name).toString();
        if (roleRecs == null || "".equals(roleRecs)) {
            //如果没有配置权限资源 默认添加一个用户权限
            roleRecs = AuthoritiesConstants.USER;
        }
        List<String> roleRecLists = Arrays.asList(roleRecs.split(","));
        Collection<? extends GrantedAuthority> authorities =
                roleRecLists.stream().map(authority -> new SimpleGrantedAuthority(authority))
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "",
                authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature: " + e.getMessage());
            return false;
        }
    }


    /**
     * 从redis获取apptoken
     *
     * @param userName
     * @return
     */
    public String getRedisAppToken(String userName) {
        return (String) redisTemplate.opsForHash().get(RedisConstant.SYSTEM_LOGIN_TOKEN, userName);
    }

    /**
     * 查询账户是否冻结
     *
     * @param userName
     * @return
     */
    public boolean isFreeze(String userName) {
        Admin admin = adminMapper.findUserInfo(userName);
        return admin == null ? true : false;
    }

}
