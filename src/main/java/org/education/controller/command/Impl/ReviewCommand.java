package org.education.controller.command.Impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.education.beans.Attributes;
import org.education.beans.Movie;
import org.education.beans.User;
import org.education.beans.UserPrincipal;
import org.education.controller.command.Command;
import org.education.service.ReviewService;
import org.education.service.ServiceFactory;
import org.education.service.UserService;
import org.education.service.exception.ServiceException;

import java.io.IOException;
import java.util.Optional;

public class ReviewCommand implements Command {

    UserService userService = ServiceFactory.getInstance().getUserService();
    ReviewService reviewService = ServiceFactory.getInstance().getReviewService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute(Attributes.USER_PRINCIPAL);
        int mark = Integer.parseInt(request.getParameter("mark"));
        String review = request.getParameter("review");
        try {
            Optional<User> user = userService.getUserByUsername(userPrincipal.getUsername());
            if(user.isEmpty()){
                return "WEB-INF/view/signIn.jsp";
            }
            reviewService.addOrChangeReview((Movie) request.getSession().getAttribute(Attributes.MOVIE),user.get(),mark,review);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
