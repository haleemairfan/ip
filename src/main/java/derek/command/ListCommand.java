package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;
import derek.task.TaskList;

/**
 * The {@code ListCommand} class handles the user's command to list all tasks.
 * It extends the {@code Command} class and executes the command to display the task list.
 */
public class ListCommand extends Command {


    /**
     * Constructs a {@code ListCommand} with the specified user command, storage, and UI.
     *
     * @param command the user command input
     * @param storage the storage object for accessing the task list
     * @param ui the UI object for interacting with the user
     */
    public ListCommand(String command, Storage storage, Ui ui) {
        super(command, storage, ui);
    }

    /**
     * Executes the {@code ListCommand} by returning the task list to the user.
     *
     * @return a string representing the list of tasks
     * @throws IncorrectCommandException if there is an issue in executing the command
     */
    @Override
    public String execute() throws IncorrectCommandException {
        TaskList taskList = this.getTaskList();
        Ui ui = this.getUi();
        return ui.returnList(taskList);
    }
}
