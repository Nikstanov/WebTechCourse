package org.education.controller.command.Impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.education.controller.command.Command;
import org.education.service.ServiceFactory;
import org.education.service.exception.ServiceException;

public class MainCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        int pageInd;
        try{
            pageInd = Integer.parseInt(request.getParameter("page"));
        }
        catch (Exception exception){
            pageInd = 0;
        }
        try {
            request.setAttribute("movies",ServiceFactory.getInstance().getMovieService().getMovies(pageInd));
            request.setAttribute("maxPage", ServiceFactory.getInstance().getMovieService().getPageCount());
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
        request.setAttribute("page", pageInd);
        return "index.jsp";
    }
}
