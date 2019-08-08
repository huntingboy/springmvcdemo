package com.nomad.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolution;

@Configuration
@EnableWebMvc
@ComponentScan({"com.nomad.web", "com.nomad.dao"})
//@PropertySource("classpath:ValidationMessages.properties")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true); //使得可以在JSP页面中通过${ }访问容器中的bean
        resolver.setViewClass(JstlView.class);  //如果需要格式化（国际化：货币、语言）,需要解析为jstl视图
        resolver.setOrder(1);
        return resolver;
    }

    //配置对静态资源的请求处理，dispatcherservlet会交给默认servlet容器而不是自己处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        super.configureDefaultServletHandling(configurer);
        configurer.enable();
    }

    //spring对thymeleaf模板的支持 th:href  "@{}":计算相对路径
    /*@Bean
    public ViewResolver viewResolver(
            TemplateEngine templateEngine
    ) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
//        viewResolver.setOrder(1);
        return viewResolver;
    }
    @Bean
    public TemplateEngine templateEngine(
            SpringResourceTemplateResolver templateResolver
    ) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        return resolver;
    }*/


    //1.可以接受@PropertySource  类似Environment   运行时值注入
    //2."${**.**}"占位符取值
    //3.国际化  类似MessageSource(须设置资源basename) ，但后者不能用在applicationContext.xml，形式为"**.**"
    /*@Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setFileEncoding("UTF-8");
        return configurer;
    }*/

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource =
                new ResourceBundleMessageSource();
        messageSource.setBasenames("messages", "ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /*
    //可以重新加载而不重启应用或重新编译
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("file:///tmp/messages");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }*/


    //配置multipart解析器
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    //servlet小于3.0版本
    //不会强制要求临时文件路径，默认情况就是servlet容器的临时目录
    //无法设置整个multipart请求的大小
    /*@Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setUploadTempDir(
                new FileSystemResource("/tmp/uploads")
        );
        multipartResolver.setMaxUploadSize(2097152);
        multipartResolver.setMaxInMemorySize(0);
        return multipartResolver;
    }*/


}
