package org.education.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.education.service.MovieService;
import org.education.service.ReviewService;
import org.education.service.UserService;
import org.education.service.exception.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.education.beans.Attributes.RETURN_PAGE;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(HttpServletRequest request, @PathVariable int id) throws ServletException {
        try {
            userService.deleteUser(id);
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
        return "redirect:/admin";
    }

    @GetMapping
    public String adminPage(HttpServletRequest request) throws ServletException {

        try {
            request.setAttribute("users",userService.getUsers());
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
        request.getSession().setAttribute(RETURN_PAGE, request.getRequestURI());
        return "admin_page";
    }


    @GetMapping("/ban/{id}")
    public String ban(HttpServletRequest request, @PathVariable int id) throws ServletException {

        try {
            userService.changeStatus(id);
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
        request.getSession().setAttribute(RETURN_PAGE, request.getRequestURI());
        return "redirect:/admin";
    }
}
