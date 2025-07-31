package com.codegym.module4casestudy.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@ComponentScan(basePackages = {
        "com.codegym.module4casestudy",
        "com.codegym.module4casestudy.config"
})
@EnableWebMvc
public class WebConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver templateResolver() {
        org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver resolver = new org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public org.thymeleaf.spring5.SpringTemplateEngine templateEngine() {
        org.thymeleaf.spring5.SpringTemplateEngine engine = new org.thymeleaf.spring5.SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    @Bean
    public ViewResolver viewResolver() {
        org.thymeleaf.spring5.view.ThymeleafViewResolver resolver = new org.thymeleaf.spring5.view.ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }
}