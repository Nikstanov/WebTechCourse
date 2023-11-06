package org.education.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.education.controller.command.Impl.*;

import java.io.IOException;
import java.util.HashMap;

public class CommandFactory {
    static private final CommandFactory commandFactory;
    static private final HashMap<String, Command> actions = new HashMap<>();

    static{
        commandFactory = new CommandFactory();
        actions.put(Commands.MAIN.toString(), new MainCommand());
        actions.put(Commands.MOVIE.toString(), new MovieCommand());
        actions.put(Commands.SIGN_UP.toString(), new SignUpCommand());
        actions.put(Commands.SIGN_IN.toString(), new SignInCommand());
        actions.put(Commands.SIGN_IN_PAGE.toString(), new SignInPageCommand());
        actions.put(Commands.SIGN_UP_PAGE.toString(), new SignUpPageCommand());
        actions.put(Commands.CHANGE_LANG.toString(), new ChangeLangCommand());
        actions.put(Commands.LOG_OUT.toString(), new LogOutCommand());
    }

    public static CommandFactory getInstance(){
        return commandFactory;
    }

    public String executeCommand(String commandName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(actions.containsKey(commandName)){
            return actions.get(commandName).execute(request, response);
        }
        return null;
    }
}
