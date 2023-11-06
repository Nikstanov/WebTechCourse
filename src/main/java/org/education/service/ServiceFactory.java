package org.education.service;

import lombok.Getter;
import org.education.service.impl.MovieServiceImpl;
import org.education.service.impl.ReviewServiceImpl;
import org.education.service.impl.UserServiceImpl;

public class ServiceFactory {

    @Getter
    private final MovieService movieService;
    @Getter
    private final UserService userService;
    @Getter
    private final ReviewService reviewService;

    private ServiceFactory(){
        movieService = new MovieServiceImpl();
        userService = new UserServiceImpl();
        reviewService = new ReviewServiceImpl();
    }

    private static final ServiceFactory serviceFactory;

    static {
        serviceFactory = new ServiceFactory();
    }

    public static ServiceFactory getInstance(){
        return serviceFactory;
    }
}
