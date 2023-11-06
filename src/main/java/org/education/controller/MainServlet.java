package org.education.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import org.apache.log4j.PropertyConfigurator;
import org.education.DAO.connection.ConnectionPool;
import org.education.DAO.exception.ConnectionException;
import org.education.beans.Attributes;
import org.education.controller.command.CommandFactory;
import org.postgresql.core.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serial;
import java.util.Properties;


@NoArgsConstructor
public class MainServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(MainServlet.class);

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Точка входа любого запроса, обрабатывает команды и переходит
     * на другие страницы в зависимости от результата выполнения команды
     * @param request запрос
     * @param response ответ
     */
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String commandName = request.getParameter(Attributes.COMMAND);
            HttpSession session = request.getSession();
            if(commandName == null){
                commandName = Attributes.DEFAULT_COMMAND;
            }
            commandName = commandName.toUpperCase();
            String path = CommandFactory.getInstance().executeCommand(commandName, request, response);
            if(path == null){
                response.sendRedirect("?command="+ session.getAttribute(Attributes.PAGE_ATTR));
            }
            else{
                session.setAttribute(Attributes.PAGE_ATTR, commandName);
                getServletContext().getRequestDispatcher("/"+path).forward(request, response);
            }
        }
        catch (ServletException | IOException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    ///Инициализация
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.getInstance().initialize();
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
        Properties logProperties = new Properties();
        try {
            logProperties.load(MainServlet.class.getClassLoader().getResourceAsStream("log4j.properties"));
        } catch (IOException e) {
            throw new ServletException(e.getMessage());
        }
        PropertyConfigurator.configure(logProperties);
        logger.info("start server " + getServletName());
    }

    @Override
    public void destroy() {
        logger.info("close server " + getServletName());
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
        super.destroy();
    }
}
