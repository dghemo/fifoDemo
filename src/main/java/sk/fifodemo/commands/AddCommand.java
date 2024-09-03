package sk.fifodemo.commands;

import sk.fifodemo.DataAccess;
import sk.fifodemo.model.User;

/**
 * This class represents a command to add a user to the database.
 * It implements the {@code ICommand} interface.
 */
public class AddCommand implements ICommand {

    private final User user;

    public AddCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute(DataAccess dataAccess) {
        System.out.println("\nAdding user...");
        dataAccess.add(user);
        System.out.println("User added: " + user);
    }
}
