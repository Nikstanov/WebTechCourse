package org.education.config;

import org.education.beans.Movie;
import org.education.beans.Review;
import org.education.beans.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import static java.lang.Boolean.TRUE;
import static org.hibernate.cfg.JdbcSettings.*;

@Configuration
@ComponentScan("org.education")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    @Bean
    @Scope("singleton")
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(User.class)
                .setProperty(DRIVER, "org.postgresql.Driver")
                .setProperty(URL, "jdbc:postgresql://localhost:5051/moviedb")
                .setProperty(USER, "Nikstanov")
                .setProperty(PASS, "15100103")
                .setProperty("hibernate.agroal.maxSize", "20")
                .setProperty(SHOW_SQL, TRUE.toString())
                .setProperty(FORMAT_SQL, TRUE.toString())
                .setProperty(HIGHLIGHT_SQL, TRUE.toString())
                .buildSessionFactory();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
    }
}
