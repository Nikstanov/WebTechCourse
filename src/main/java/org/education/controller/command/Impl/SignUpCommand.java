package org.education.controller.command.Impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.education.controller.command.Command;

public class SignUpCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("movieName","newName");
        return "WEB-INF/view/movie.jsp";
    }
}
