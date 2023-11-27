package org.education.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.education.beans.*;
import org.education.beans.dto.ReviewDTO;
import org.education.beans.security.PersonDetails;
import org.education.service.MovieService;
import org.education.service.ReviewService;
import org.education.service.exception.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.education.beans.Attributes.RETURN_PAGE;

@Controller
@RequestMapping("/")
public class MainController {

    private final MovieService movieService;
    private final ReviewService reviewService;


    public MainController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String mainPage(HttpServletRequest request) throws ServletException {

        HttpSession session = request.getSession();
        if(session.isNew()){
            session.setAttribute(Attributes.LANG_NAME_ATTRIBUTE, Languages.values()[0].toString());
            session.setAttribute(Attributes.LANG_ATTRIBUTE, 0);
            session.setMaxInactiveInterval(60*60*24);
            session.setMaxInactiveInterval(-1);
        }

        int pageInd;
        try{
            pageInd = Integer.parseInt(request.getParameter("page"));
        }
        catch (Exception exception){
            pageInd = 0;
        }
        try {
            session.setAttribute("movies",movieService.getMovies(pageInd));
            session.setAttribute("maxPage", movieService.getPageCount());
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
        session.setAttribute("page", pageInd);
        session.setAttribute(RETURN_PAGE, request.getRequestURI());
        return "main";
    }

    @GetMapping("/movie/{id}")
    public String moviePage(@PathVariable(name = "id") int movie_id, HttpServletRequest request, @ModelAttribute("review") ReviewDTO review) throws ServletException {

        try{
            Optional<Movie> movie = movieService.getMovieById(movie_id);
            if(movie.isEmpty()) {
                throw new ServletException("Unknown movie");
            }

            request.getSession().setAttribute(Attributes.MOVIE, movie.get());
        }
        catch (NumberFormatException e){
            throw new ServletException(e.getMessage());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute(RETURN_PAGE, request.getRequestURI());
        return "movie";
    }


    @GetMapping("/lang")
    public String changeLang(HttpServletRequest request){
        HttpSession session = request.getSession();
        int newInd = ((int)session.getAttribute(Attributes.LANG_ATTRIBUTE) + 1) % Languages.values().length;
        session.setAttribute(Attributes.LANG_NAME_ATTRIBUTE, Languages.values()[newInd].toString());
        session.setAttribute(Attributes.LANG_ATTRIBUTE, newInd);
        return "redirect:" + session.getAttribute(RETURN_PAGE);
    }



    @PostMapping("/review")
    public String addReview(@SessionAttribute(name = "movie") Movie movie, @ModelAttribute("review") ReviewDTO reviewDTO, BindingResult bindingResult, Authentication authentication){
        try {
            reviewService.addOrChangeReview(movie,((PersonDetails)(authentication.getPrincipal())).getUser(),reviewDTO.getMark(),reviewDTO.getReview());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/movie/"+movie.getId();
    }


}
