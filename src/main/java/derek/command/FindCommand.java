package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.TaskNotFoundException;
import derek.task.Task;
import derek.task.TaskList;

/**
 * The {@code FindCommand} class searches for a task by its description in the task list.
 * It extends the {@code Command} class and executes the command to find a task.
 */
public class FindCommand extends Command {

    private String taskDescription;
    private Ui ui;

    /**
     * Constructs a {@code FindCommand} with the specified user command, storage, and UI.
     *
     * @param command the user command input
     * @param storage the storage object for accessing the task list
     * @param ui the UI object for interacting with the user
     */
    public FindCommand(String command, Storage storage, Ui ui) {
        super(command, storage);
        this.ui = ui;
    }

    /**
     * Extracts the task description from the user command.
     */
    public void getTaskDescription() {
        String command = super.getCommand();
        int firstWord = command.indexOf(" ");
        this.taskDescription = command.substring(firstWord + 1);
    }

    /**
     * Executes the {@code FindCommand} by searching for the task in the task list.
     * If the task is found, its details are printed. If not, an exception is thrown.
     *
     * @return a message with the task details or an error message if the task is not found
     */
    public String execute() {
        try {
            TaskList taskList = this.getTaskList();
            this.getTaskDescription();
            Task task = this.findTask(taskList);
            return this.printTask(task);
        } catch (TaskNotFoundException e) {
            return e.getMessage();
        }
    }

    /**
     * Searches for the task in the task list by its description.
     *
     * @param taskList the task list to search in
     * @return the {@code Task} object that matches the description
     * @throws TaskNotFoundException if the task is not found
     */
    public Task findTask(TaskList taskList) throws TaskNotFoundException {
        return taskList.find(this.taskDescription);
    }

    /**
     * Prints the details of the found task.
     *
     * @param task the task that was found
     * @return the message with the task details
     */
    public String printTask(Task task) {
        return ui.printTask(task);
    }
}
