package org.education.controller.command.Impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.education.controller.command.Command;
import org.education.service.ServiceFactory;
import org.education.service.UserService;
import org.education.service.exception.ServiceException;

import java.io.IOException;

public class DeleteCommand implements Command {

    UserService userService = ServiceFactory.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            userService.deleteUser(Integer.parseInt(request.getParameter("id")));
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
        return null;
    }
}
