package sk.fifodemo.commands;

import sk.fifodemo.DataAccess;

/**
 * This class represents a command to delete all users from the database.
 * It implements the {@code ICommand} interface.
 */
public class DeleteAllCommand implements ICommand {
    @Override
    public void execute(DataAccess dataAccess) {
        System.out.println("\nDeleting all users...");
        dataAccess.deleteAll();
        System.out.println("Deleted all users");
    }
}
