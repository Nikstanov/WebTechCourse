package org.education.controller.command.Impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.education.beans.Attributes;
import org.education.beans.UserPrincipal;
import org.education.controller.command.Command;
import org.education.service.ServiceFactory;
import org.education.service.UserService;
import org.education.service.exception.AlreadyExistException;
import org.education.service.exception.ServiceException;


public class SignUpCommand implements Command {

    UserService userService = ServiceFactory.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        try {
            UserPrincipal newUser = userService.newUser(email,password,username);
            request.getSession().setAttribute(Attributes.USER_PRINCIPAL, newUser);
            request.setAttribute("role", newUser.getRole().toString());
            return "index.jsp";
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (AlreadyExistException e) {
            request.setAttribute("Error", "User already exist");
            return "WEB-INF/view/signUp.jsp";
        }
    }
}
