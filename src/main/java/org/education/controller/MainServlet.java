package org.education.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.education.DAO.connection.ConnectionPool;
import org.education.DAO.exception.ConnectionException;
import org.education.controller.command.CommandFactory;
import java.io.IOException;
import java.io.Serial;


@NoArgsConstructor
public class MainServlet extends HttpServlet {

    private static final String COMMAND = "command";
    private static final String DEFAULT_COMMAND = "main";
    private static final String DEFAULT_PAGE = "index.jsp";

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

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);
        if(commandName == null){
            commandName = DEFAULT_COMMAND;
        }
        commandName = commandName.toUpperCase();
        String path = CommandFactory.getInstance().executeCommand(commandName, request, response);
        if(path == null){
            getServletContext().getRequestDispatcher("/"+DEFAULT_PAGE).forward(request, response);

        }
        else{
            getServletContext().getRequestDispatcher("/"+path).forward(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.getInstance().initialize();
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
        super.destroy();
    }
}
