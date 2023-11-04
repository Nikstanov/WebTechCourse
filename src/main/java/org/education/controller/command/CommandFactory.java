package org.education.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.education.controller.command.Impl.MainCommand;
import org.education.controller.command.Impl.MovieCommand;
import org.education.controller.command.Impl.SignUpCommand;

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
