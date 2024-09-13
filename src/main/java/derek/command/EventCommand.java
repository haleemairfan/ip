package derek.command;

import derek.*;
import derek.exception.IncorrectCommandException;
import derek.task.Task;

import java.time.format.DateTimeParseException;

/**
 * The {@code EventCommand} class adds an event task to the task list.
 * It extends the {@code TaskCommand} class and executes the command to add the event task.
 */
public class EventCommand extends TaskCommand {

    /**
     * Constructs an {@code EventCommand} with the specified user command, storage, and UI.
     *
     * @param command the user command input
     * @param storage the storage object for accessing the task list
     * @param ui the UI object for interacting with the user
     */
    public EventCommand(String command, Storage storage, Ui ui) {
        super(command, storage, ui);
    }

    /**
     * Executes the {@code EventCommand} by adding the event task to the task list.
     * The task description and event times are extracted and validated.
     *
     * @return a message indicating that the task has been added
     * @throws IncorrectCommandException if the command is not formatted correctly
     * @throws DateTimeParseException if the date format is incorrect
     */
    @Override
    public String execute() throws IncorrectCommandException, DateTimeParseException {
        String name = this.getTask();
        String[] taskDescription = name.split("/from");
        this.validateTaskDescription(taskDescription);
        String[] time = taskDescription[1].split("/to");
        this.validateCommand(taskDescription, time);
        Task task = this.createTask(taskDescription[0], time[0], time[1]);
        this.addTask(task);
        return this.printAddTask(task);
    }

    /**
     * Validates the task description to ensure the correct format is used.
     *
     * @param taskDescription the task description split by "/from"
     * @throws IncorrectCommandException if the command format is incorrect
     */
    public void validateTaskDescription(String[] taskDescription) throws IncorrectCommandException {
        if (taskDescription.length != 2) {
            throw new IncorrectCommandException("Please enter your commands correctly "
                    + "for Derek (event (task) /from (time) /to (time)");
        }
    }

    /**
     * Validates the command to ensure both task description and times are present.
     *
     * @param taskDescription the task description
     * @param time the start and end times of the event
     * @throws IncorrectCommandException if the command format is incorrect
     */
    public void validateCommand(String[] taskDescription, String[] time) throws IncorrectCommandException {
        if (taskDescription.length + time.length != 4) {
            throw new IncorrectCommandException("Please enter your commands correctly "
                    + "for Derek (event (task) /from (time) /to (time)");
        }
    }

    /**
     * Creates an event task from the provided task description and times.
     *
     * @param taskDescription the task description
     * @param from the start time of the event
     * @param to the end time of the event
     * @return the created {@code Task} object
     */
    public Task createTask(String taskDescription, String from, String to) {
        return Task.eventTask(taskDescription, from, to);
    }
}
