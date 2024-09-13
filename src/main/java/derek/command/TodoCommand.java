package derek.command;

import derek.Storage;
import derek.Ui;
import derek.task.Task;

/**
 * The {@code TodoCommand} class adds a todo task to the task list.
 * It extends the {@code TaskCommand} class and executes the command to add the todo task.
 */
public class TodoCommand extends TaskCommand {

    /**
     * Constructs a {@code TodoCommand} with the specified user command, storage, and UI.
     *
     * @param command the user command input
     * @param storage the storage object for accessing the task list
     * @param ui the UI object for interacting with the user
     */
    public TodoCommand(String command, Storage storage, Ui ui) {
        super(command, storage, ui);
    }

    /**
     * Executes the {@code TodoCommand} by creating and adding the todo task to the task list.
     *
     * @return a message indicating that the todo task has been added
     */
    public String execute() {
        String name = this.getTask();
        Task task = this.createTask(name);
        this.addTask(task);
        return this.printAddTask(task);
    }

    /**
     * Creates a todo task with the given name.
     *
     * @param name the name of the todo task
     * @return the created {@code Task} object
     */
    public Task createTask(String name) {
        return Task.toDoTask(name);
    }
}
