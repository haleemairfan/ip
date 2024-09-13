package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;
import derek.task.Task;
import derek.task.TaskList;

/**
 * The {@code IncompleteCommand} class marks a task as incomplete in the task list.
 * It extends the {@code Command} class and executes the command to mark a task as incomplete.
 */
public class IncompleteCommand extends Command {


    /**
     * Constructs an {@code IncompleteCommand} with the specified user command, storage, UI, and task list size.
     *
     * @param command the user command input
     * @param storage the storage object for accessing the task list
     * @param ui the UI object for interacting with the user
     */
    public IncompleteCommand(String command, Storage storage, Ui ui) {
        super(command, storage, ui);
    }

    /**
     * Executes the {@code IncompleteCommand} by marking the specified task as incomplete.
     * The task number is extracted from the user command and validated.
     *
     * @return a message indicating that the task has been marked as incomplete
     * @throws IncorrectCommandException if the task number is invalid
     */
    @Override
    public String execute() throws IncorrectCommandException {
        String command = this.getCommand();
        String[] words = command.split("\\s+");
        int taskNumber = this.getTaskNumber(words[1]);
        this.validateTaskNumber(taskNumber);
        Task task = getTask(taskNumber - 1);
        task.markIncomplete();
        return this.markIncompleteTask(task);
    }

    /**
     * Extracts the task number from the user input.
     *
     * @param taskNumber the string representation of the task number
     * @return the task number as an integer
     */
    public int getTaskNumber(String taskNumber) {
        return Integer.valueOf(taskNumber);
    }

    /**
     * Validates the task number to ensure it falls within the valid range.
     *
     * @param taskNumber the task number to be validated
     * @throws IncorrectCommandException if the task number is out of bounds
     */
    public void validateTaskNumber(int taskNumber) throws IncorrectCommandException {
        int sizeOfTaskList = this.getSizeOfTaskList();
        // Explicitly check the condition and throw exception if needed
        if (taskNumber < 1 || taskNumber > sizeOfTaskList) {
            throw new IncorrectCommandException("Do you not know how to count?");
        }
    }

    /**
     * Retrieves the task from the task list by its index.
     *
     * @param index the index of the task in the task list
     * @return the {@code Task} object to be marked as incomplete
     */
    public Task getTask(int index) {
        TaskList taskList = this.getTaskList();
        Task task = taskList.get(index);
        return task;
    }

    /**
     * Marks the task as incomplete and returns the UI response.
     *
     * @param task the task to be marked as incomplete
     * @return the message confirming the task has been marked incomplete
     */
    public String markIncompleteTask(Task task) {
        Ui ui = this.getUi();
        return ui.incompleteTask(task);
    }

}
