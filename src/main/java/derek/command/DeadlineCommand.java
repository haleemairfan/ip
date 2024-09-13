package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;
import derek.task.Task;

import java.time.format.DateTimeParseException;

/**
 * The {@code DeadlineCommand} class adds a deadline task to the task list.
 * It extends the {@code TaskCommand} class and executes the command to add the deadline task.
 */
public class DeadlineCommand extends TaskCommand {

    /**
     * Constructs a {@code DeadlineCommand} with the specified user command, storage, and UI.
     *
     * @param command the user command input
     * @param storage the storage object for accessing the task list
     * @param ui the UI object for interacting with the user
     */
    public DeadlineCommand(String command, Storage storage, Ui ui) {
        super(command, storage, ui);
    }

    /**
     * Executes the {@code DeadlineCommand} by adding the deadline task to the task list.
     * The task description and deadline date are extracted from the user command.
     *
     * @return a message indicating that the task has been added
     * @throws IncorrectCommandException if the command is not formatted correctly
     * @throws DateTimeParseException if the date format is incorrect
     */
    @Override
    public String execute() throws IncorrectCommandException, DateTimeParseException {
        String name = this.getTask();
        String[] information = name.split("/by");
        this.validateCommand(information);
        Task task = this.createTask(information);
        this.addTask(task);
        return this.printAddTask(task);
    }

    /**
     * Validates the user command to ensure the correct format is used.
     *
     * @param information the task description and deadline date split by "/by"
     * @throws IncorrectCommandException if the command format is incorrect
     */
    public void validateCommand(String[] information) throws IncorrectCommandException {
        if (information.length != 2) {
            throw new IncorrectCommandException("Please enter your commands correctly"
                    + " for Derek (deadline (task) /by (date))");
        }
    }

    /**
     * Creates a new deadline task from the extracted information.
     *
     * @param information the task description and deadline date
     * @return the created {@code Task} object
     */
    public Task createTask(String[] information) {
        return Task.deadlineTask(information[0], information[1]);
    }
}
