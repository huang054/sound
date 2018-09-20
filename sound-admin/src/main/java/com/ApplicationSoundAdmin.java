package com;

import com.config.DefaultProfileUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author music
 */

@SpringBootApplication
@MapperScan("com.dao.mapper")
public class ApplicationSoundAdmin {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSoundAdmin.class);


    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(ApplicationSoundAdmin.class);
        /*
            默认为开发环境：dev
         */
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        logger.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 正在运行! 链接地址为:\n\t" +
                        "swagger-ui:\thttp://localhost:{}{}/swagger-ui.html\n\t" +
                        "本地地址: \thttp://localhost:{}\n\t" +
                        "网络地址: \thttp://{}:{}\n----------------------------------------------------------",

                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }
}   