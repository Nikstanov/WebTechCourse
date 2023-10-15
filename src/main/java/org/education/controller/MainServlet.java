package org.education.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.lang.reflect.Executable;
import java.util.HashMap;

public class MainServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    static private HashMap<String, Executable> actions = new HashMap<>();

    public MainServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
