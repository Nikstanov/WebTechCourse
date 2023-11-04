package org.education.controller.command.Impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.education.beans.Movie;
import org.education.controller.command.Command;
import org.education.service.ServiceFactory;
import org.education.service.exception.ServiceException;
import java.util.Optional;

public class MovieCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            Optional<Movie> movie = ServiceFactory.getInstance().getMovieService().getMovieById(id);
            if(movie.isEmpty()){
                throw new ServletException("Unknown movie");
            }
            request.setAttribute("movie", movie.get());
        }
        catch (NumberFormatException e){
            throw new ServletException(e.getMessage());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        return "WEB-INF/view/movie.jsp";
    }
}
