package com.nomad.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//1.servlet3.0，容器会在类路径下查找ServletContainerInitializer实现类，用来配置servlet容器
//2.ServletContainerInitializer ===》(实现) SpringServletContainerInitializer ==》(调用) WebApplicationInitializer的实现类
//3.使用默认的DispatcherServlet
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
