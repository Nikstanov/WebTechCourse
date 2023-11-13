package org.education.controller.command.Impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.education.beans.Attributes;
import org.education.beans.UserPrincipal;
import org.education.controller.command.Command;
import org.education.service.ServiceFactory;
import org.education.service.UserService;
import org.education.service.exception.AlreadyExistException;
import org.education.service.exception.ServiceException;
import org.education.service.exception.UnknownUserException;

import java.io.IOException;

public class SignInCommand implements Command {

    UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            UserPrincipal userPrincipal = userService.authenticate(email,password);
            if(userPrincipal == null){
                request.setAttribute("Error", "Incorrect password");
                return "WEB-INF/view/signIn.jsp";
            }
            request.getSession().setAttribute(Attributes.USER_PRINCIPAL, userPrincipal);
            request.setAttribute("role", userPrincipal.getRole().toString());
            return "index.jsp";
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (UnknownUserException e) {
            request.setAttribute("Error", e.getMessage());
            return "WEB-INF/view/signIn.jsp";
        }
    }
}
