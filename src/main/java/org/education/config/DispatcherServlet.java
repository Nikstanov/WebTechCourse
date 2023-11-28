package org.education.config;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;
import org.education.DAO.exception.ConnectionException;
import org.education.service.UserService;
import org.education.service.exception.ServiceException;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new DelegatingFilterProxy()};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        super.onStartup(servletContext);
        registerHiddenFieldFilter(servletContext);

        Properties logProperties = new Properties();
        try {
            logProperties.load(DispatcherServlet.class.getClassLoader().getResourceAsStream("log4j.properties"));
        } catch (IOException e) {
            throw new ServletException(e.getMessage());
        }
        PropertyConfigurator.configure(logProperties);
        log.info("start server " + getServletName());

        var scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(
                () -> {
                    try {
                        ApplicationContextHolder.getApplicationContext().getBean(UserService.class).updateRate();
                    } catch (ServiceException e) {
                        log.error(e.getMessage());
                    }
                },
                5,5, TimeUnit.MINUTES);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }


}
