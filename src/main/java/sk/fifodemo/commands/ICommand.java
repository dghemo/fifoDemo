package sk.fifodemo.commands;

import sk.fifodemo.DataAccess;

/**
 * Represents a command interface that can be executed with a given DataAccess object.
 * Classes implementing this interface should provide specific functionality
 * in the execute method.
 */
public interface ICommand {
    void execute(DataAccess dataAccess);
}
