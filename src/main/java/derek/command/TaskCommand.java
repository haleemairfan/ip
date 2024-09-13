package derek.command;

import derek.Storage;
import derek.Ui;
import derek.task.Task;
import derek.task.TaskList;

/**
 * The {@code TaskCommand} class is an abstract class that represents a command related to tasks.
 * It extends the {@code Command} class and provides methods to extract the task details
 * from the command and interact with the task list.
 */
public abstract class TaskCommand extends Command {


    /**
     * Constructs a {@code TaskCommand} with the specified user command, storage, and UI.
     *
     * @param command the user command input
     * @param storage the storage object for accessing the task list
     * @param ui the UI object for interacting with the user
     */
    public TaskCommand(String command, Storage storage, Ui ui) {
        super(command, storage, ui);
    }

    /**
     * Extracts the task details from the user command.
     *
     * @return the task details as a string
     */
    public String getTask() {
        String command = super.getCommand();
        int firstWord = command.indexOf(" ");
        return command.substring(firstWord + 1);
    }

    /**
     * Adds the given task to the task list.
     *
     * @param task the task to be added to the task list
     */
    public void addTask(Task task) {
        TaskList taskList = this.getTaskList();
        taskList.add(task);
    }

    /**
     * Prints a message to confirm that the task has been added.
     *
     * @param task the task that was added
     * @return a string message confirming the task has been added
     */
    public String printAddTask(Task task) {
        Ui ui = this.getUi();
        return ui.addTask(task);
    }
}
