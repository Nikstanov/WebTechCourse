package org.education.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Command{

    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
