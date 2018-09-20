package com;

import com.config.DefaultProfileUtil;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 这是创建 web.xml 的辅助类
 * 这个类只有在部署到类似Tomcat, Jboss等的环境中才会被调用
 * @author music
 */
public class ApplicationWebXml extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        /**
         * 如果没有设置环境则设置为默认环境
         */
        DefaultProfileUtil.addDefaultProfile(application.application());
        return application.sources(ApplicationSoundAdmin.class);
    }
}
