package com.nomad.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

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

    //配置multipart的具体细节
    //上传文件临时存放路径
    //上传文件大小L<20MB
    //multipart请求大小<40MB
    //及时写入磁盘
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        String path = "/tmp/uploads";
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        registration.setMultipartConfig(  //临时目录一定要存在，否则异常  
                new MultipartConfigElement(path,
                        20971520, 41943040, 0)
        );
    }
}
