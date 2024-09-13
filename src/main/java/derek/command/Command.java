package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;
import derek.task.TaskList;

/**
 * The {@code Command} class processes user input commands and provides methods to interact
 * with the storage and task list. It serves as a base class for specific commands
 * like {@code CompleteCommand}, {@code DeadlineCommand}, etc.
 */
public abstract class Command {
    private String command;
    private Storage storage;

    /**
     * Constructs a {@code Command} object with the specified command string and storage.
     *
     * @param command the command input by the user
     * @param storage the storage object used to interact with the task list
     */
    public Command(String command, Storage storage) {
        this.command = command;
        this.storage = storage;
    }

    /**
     * Returns the command string.
     *
     * @return the command string input by the user
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Returns the storage object.
     *
     * @return the {@code Storage} object used to interact with the task list
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Returns the task list from the storage.
     *
     * @return the {@code TaskList} object containing all tasks
     */
    public TaskList getTaskList() {
        return this.storage.getTaskList();
    }

    /**
     * Executes the command. Each subclass must provide its own implementation.
     *
     * @return a string indicating the result of executing the command
     * @throws IncorrectCommandException if there is an error in command execution
     */
    public abstract String execute() throws IncorrectCommandException;
}
