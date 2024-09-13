package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;
import derek.task.Task;
import derek.task.TaskList;

/**
 * The {@code CompleteCommand} class marks a task as completed in the task list.
 * It extends the {@code Command} class and executes the command to complete a task.
 */
public class CompleteCommand extends Command {

    private Ui ui;
    private int sizeOfTaskList;

    /**
     * Constructs a {@code CompleteCommand} with the specified user command, storage, UI, and task list size.
     *
     * @param command the user command input containing the task number to be marked as complete
     * @param storage the storage object for accessing the task list
     * @param ui the UI object for interacting with the user
     * @param sizeOfTaskList the size of the current task list
     */
    public CompleteCommand(String command, Storage storage, Ui ui, int sizeOfTaskList) {
        super(command, storage);
        this.ui = ui;
        this.sizeOfTaskList = sizeOfTaskList;
    }

    /**
     * Executes the {@code CompleteCommand} by marking the specified task as completed.
     * The task number is extracted from the user command. If the task number is invalid,
     * an {@code IncorrectCommandException} is thrown.
     *
     * @return a message indicating that the task has been marked as completed
     * @throws IncorrectCommandException if the task number is out of bounds
     */
    @Override
    public String execute() throws IncorrectCommandException {
        String command = this.getCommand();
        String[] words = command.split("\\s+");
        int taskNumber = this.getTaskNumber(words[1]);
        this.validateTaskNumber(taskNumber);
        Task task = this.getTask(taskNumber - 1);
        task.markCompleted();
        return this.completeTask(task);
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
     * @throws IncorrectCommandException if the task number is less than 1 or greater than the task list size
     */
    public void validateTaskNumber(int taskNumber) throws IncorrectCommandException {
        if (taskNumber < 1 || taskNumber > this.sizeOfTaskList) {
            throw new IncorrectCommandException("do you not know how to count??");
        }
    }

    /**
     * Retrieves the task from the task list by its index.
     *
     * @param index the index of the task in the task list
     * @return the {@code Task} object to be marked as complete
     */
    public Task getTask(int index) {
        TaskList taskList = this.getTaskList();
        Task task = taskList.get(index);
        return task;
    }

    /**
     * Marks the task as complete and returns the UI response.
     *
     * @param task the task to be marked as completed
     * @return the message confirming the task completion
     */
    public String completeTask(Task task) {
        return this.ui.completeTask(task);
    }
}
