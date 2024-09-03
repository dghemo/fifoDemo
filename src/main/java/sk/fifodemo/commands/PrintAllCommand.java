package sk.fifodemo.commands;

import sk.fifodemo.DataAccess;
import sk.fifodemo.model.User;

import java.util.List;

/**
 * This class represents a command to print all users from the database.
 * It implements the {@code ICommand} interface.
 */
public class PrintAllCommand implements ICommand {
    @Override
    public void execute(DataAccess dataAccess) {
        System.out.println("\nPrinting all users...");
        List<User> users = dataAccess.getAll();
        if(users.isEmpty()) {
            System.out.println("No users found");
        }
        users.forEach(System.out::println);
    }
}
