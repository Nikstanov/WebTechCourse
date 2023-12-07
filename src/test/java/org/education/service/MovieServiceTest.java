package org.education.service;

import lombok.SneakyThrows;
import org.education.DAO.MovieDAO;
import org.education.beans.Movie;
import org.education.service.exception.ServiceException;
import org.education.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieServiceTest {

    @Mock
    MovieDAO movieDAO;

    @InjectMocks
    MovieServiceImpl movieService;

    @Test
    public void getMoviesTestPagination(){
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            movies.add(Movie.builder().build());
        }
        when(movieDAO.findAll()).thenReturn(movies);
        List<Movie> res = null;
        try {
            res = movieService.getMovies(1);
        } catch (ServiceException e) {
            Assertions.fail();
        }
        Assertions.assertEquals(movies.subList(3, 6), res);
        verify(movieDAO).findAll();
    }

    @Test
    public void getPageCountTest(){
        when(movieDAO.count()).thenReturn(10L);
        int res = 0;
        try {
            res = movieService.getPageCount();
        } catch (ServiceException e)     {
            Assertions.fail();
        }
        Assertions.assertEquals(3, res);
        verify(movieDAO).count();
    }
}
