package derek;

import java.time.format.DateTimeParseException;
import derek.command.*;
import derek.exception.IncorrectCommandException;

/**
 * The {@code Parser} class handles the interpretation and execution of user commands.
 * It processes commands, determines their types, and directs them to the appropriate
 * command classes for execution.
 */
public class Parser {

    private String command;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a {@code Parser} object with the specified command, storage, and UI.
     *
     * @param command the user command input
     * @param storage the storage object containing the task list
     * @param ui the UI object to interact with the user
     */
    public Parser(String command, Storage storage, Ui ui) {
        this.command = command;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses and executes the user command by splitting it to identify the command type,
     * and then calling the appropriate command class.
     * If the command is invalid, an error message is returned.
     *
     * @return the result of the command execution
     * @throws IncorrectCommandException if the command is invalid
     * @throws DateTimeParseException if there is a date format error in deadline/event commands
     */
    public String getCommand() throws IncorrectCommandException, DateTimeParseException {
        String[] words = this.command.split("\\s+");
        String tag = words[0].toUpperCase();
        return processCommand(tag);
    }

    /**
     * Processes the command by determining its type using the {@code CommandEnums} enum
     * and directing it to the appropriate command class for execution.
     *
     * @param tag the user input that identifies the command type
     * @return the result of the command execution
     * @throws IncorrectCommandException if the command is invalid
     * @throws DateTimeParseException if there is a date format error in deadline/event commands
     */
    public String processCommand(String tag) throws IncorrectCommandException, DateTimeParseException {
        Command processedCommand;
        int sizeOfTaskList = this.getSizeOfTaskList();
        try {
            switch (CommandEnums.valueOf(tag)) {
                case BYE:
                    processedCommand = new LeavingCommand(this.command, this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                case LIST:
                    processedCommand = new ListCommand(this.command, this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                case DELETE:
                    processedCommand = new DeleteCommand(this.command, this.storage, this.ui, sizeOfTaskList);
                    return this.executeCommand(processedCommand);
                case MARK:
                    processedCommand = new CompleteCommand(this.command, this.storage, this.ui, sizeOfTaskList);
                    return this.executeCommand(processedCommand);
                case UNMARK:
                    processedCommand = new IncompleteCommand(this.command, this.storage, this.ui, sizeOfTaskList);
                    return this.executeCommand(processedCommand);
                case FIND:
                    processedCommand = new FindCommand(this.command, this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                case DEADLINE:
                    processedCommand = new DeadlineCommand(this.command, this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                case EVENT:
                    processedCommand = new EventCommand(this.command, this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                case TODO:
                    processedCommand = new TodoCommand(this.command, this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                default:
                    throw new IncorrectCommandException("Please respond Y or N, Derek does not appreciate stupid people.");
            }
        } catch (IllegalArgumentException e) {
            throw new IncorrectCommandException("Is it a todo, event, or deadline?\n"
                    + "Please enter your commands correctly for Derek (e.g. todo (task)), he keeps throwing tantrums.");
        }
    }

    /**
     * Executes the provided command by calling its {@code execute()} method.
     *
     * @param command the {@code Command} object to be executed
     * @return the result of the command execution
     * @throws IncorrectCommandException if there is an error during command execution
     */
    public String executeCommand(Command command) throws IncorrectCommandException {
        return command.execute();
    }

    /**
     * Handles user consent by processing "Y" (yes) or "N" (no) responses.
     * It directs the user input to either the {@code ConsentCommand} or {@code DeclineCommand}.
     *
     * @return the result of the consent command execution
     * @throws IncorrectCommandException if the response is invalid
     */
    public String getConsent() throws IncorrectCommandException {
        String command = this.command.toUpperCase();
        Command processedCommand;
        try {
            switch (CommandEnums.valueOf(command)) {
                case Y:
                    processedCommand = new ConsentCommand(this.command, this.ui, this.storage);
                    return this.executeCommand(processedCommand);
                case N:
                    processedCommand = new DeclineCommand(this.command, this.ui, this.storage);
                    return this.executeCommand(processedCommand);
                default:
                    throw new IncorrectCommandException("Please respond Y or N, Derek does not appreciate stupid people.");
            }
        } catch (IllegalArgumentException e) {
            throw new IncorrectCommandException("Please respond Y or N, Derek does not appreciate stupid people.");
        }
    }

    /**
     * Retrieves the size of the current task list from storage.
     *
     * @return the number of tasks in the task list
     */
    public int getSizeOfTaskList() {
        return this.storage.getTaskListSize();
    }
}
