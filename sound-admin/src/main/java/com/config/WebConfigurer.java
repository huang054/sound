package com.config;

import com.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.inject.Inject;
import javax.servlet.*;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * 使用 Servlet 3.0 进行 Web 配置
 *
 * @author music
 */
@Configuration
public class WebConfigurer  implements ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Inject
    private Environment env;

    @Inject
    private TokenProvider tokenProvider;


    /**
     * 解析静态资源的前缀
     */
    private String resolvePathPrefix() {
        String fullExecutablePath = this.getClass().getResource("").getPath();
        String rootPath = Paths.get(".").toUri().normalize().getPath();
        String extractedPath = fullExecutablePath.replace(rootPath, "");
        int extractionEndIndex = extractedPath.indexOf("target/");
        if (extractionEndIndex <= 0) {
            return "";
        }
        return extractedPath.substring(0, extractionEndIndex);
    }


    @Bean
    @ConditionalOnProperty(name = "project.cors.allowed-origins")
    public CorsFilter corsFilter() {
        log.debug("注册 CORS 过滤器");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        ArrayList<String> list = new ArrayList<>();
        list.add("*");
        config.setAllowedHeaders(list);
        config.setAllowedOrigins(list);
        ArrayList<String> methods = new ArrayList<>();
        methods.add("GET");
        methods.add("PUT");
        methods.add("POST");
        methods.add("DELETE");
        methods.add("OPTIONS");
        config.setAllowedMethods(methods);
        config.setMaxAge(1800L);
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/v2/api-docs", config);
        source.registerCorsConfiguration("/oauth/**", config);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }
}
