package org.education.service;

import lombok.Getter;
import org.education.service.impl.MovieServiceImpl;

public class ServiceFactory {

    @Getter
    private final MovieService movieService;

    private ServiceFactory(){
        movieService = new MovieServiceImpl();
    }

    private static final ServiceFactory serviceFactory;

    static {
        serviceFactory = new ServiceFactory();
    }

    public static ServiceFactory getInstance(){
        return serviceFactory;
    }
}
