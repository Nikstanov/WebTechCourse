package org.education.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.education.beans.Attributes;
import org.education.beans.Languages;

import java.io.IOException;

public class MainFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if(session.isNew()){
            session.setAttribute(Attributes.LANG_NAME_ATTRIBUTE, Languages.values()[0].toString());
            session.setAttribute(Attributes.LANG_ATTRIBUTE, 0);
            session.setMaxInactiveInterval(60*60*24);
            session.setMaxInactiveInterval(-1);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
