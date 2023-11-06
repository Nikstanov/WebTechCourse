package org.education.controller.command.Impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.education.beans.Attributes;
import org.education.beans.Role;
import org.education.controller.command.Command;

import java.io.IOException;

public class SignOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(Attributes.USER_PRINCIPAL);
        request.setAttribute("role", Role.NOT_AUTH.toString());
        return "index.jsp";
    }
}
