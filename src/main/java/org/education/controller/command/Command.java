package org.education.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Command{

    /**
     * Выполняет заданную команду
     *
     * @param request запрос, передается сервлетом
     * @param response ответ, передается сервлетом
     * @return String - файл view на который нужно перейти, null - переадресация на ту же страницу
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
