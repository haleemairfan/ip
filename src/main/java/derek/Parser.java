package derek;

import java.time.format.DateTimeParseException;

import derek.command.*;
import derek.exception.IncorrectCommandException;
import derek.task.Task;


/**
 * The {@code Parser} class handles the interpretation and execution of user commands.
 * It processes commands and directs them to the appropriate command classes.
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
     * Parses and executes the user command.
     * It determines the type of command and calls the appropriate command class.
     * If the command is invalid, an error message is printed.
     */
    public String getCommand() {
        try {
            if (this.command.equals("bye")) {
                LeavingCommand leavingCommand = new LeavingCommand(this.command);
                return leavingCommand.execute(this.storage, this.ui);
            } else if (this.command.equals("list")) {
                ListCommand listCommand = new ListCommand(this.command);
                return listCommand.execute(this.storage, this.ui);
            } else if (this.command.equalsIgnoreCase("Y")) {
                ConsentCommand consentCommand = new ConsentCommand(this.command);
                return consentCommand.execute(this.ui);
            } else if(this.command.equalsIgnoreCase("N")) {
                DeclineCommand declineCommand = new DeclineCommand(this.command);
                return declineCommand.execute(this.ui);
            } else {
                int size = this.storage.getTaskListSize();
                String[] words = command.split("\\s+");
                if (words.length > 1) {
                    if (words[0].equals("delete")) {
                        int taskNumber = Integer.valueOf(words[1]);
                        if (taskNumber < 1 || taskNumber > size) {
                            throw new IncorrectCommandException("do you not know how to count????");
                        }
                        DeleteCommand deleteCommand = new DeleteCommand(this.command);
                        return deleteCommand.execute(taskNumber, this.storage, this.ui);
                    } else if (words[0].equals("mark")) {
                        int taskNumber = Integer.valueOf(words[1]);
                        if (taskNumber < 1 || taskNumber > size) {
                            throw new IncorrectCommandException("do you not know how to count????");
                        }
                        CompleteCommand completeCommand = new CompleteCommand(this.command);
                        return completeCommand.execute(this.storage, taskNumber, this.ui);
                    } else if (words[0].equals("unmark")) {
                        int taskNumber = Integer.valueOf(words[1]);
                        if (taskNumber < 1 || taskNumber > size) {
                            throw new IncorrectCommandException("do you not know how to count????");
                        }
                        IncompleteCommand incompleteCommand = new IncompleteCommand(this.command);
                        return incompleteCommand.execute(this.storage, taskNumber, this.ui);
                    } else if (words[0].equals("find")) {
                        FindCommand findCommand = new FindCommand(this.command);
                        return findCommand.execute(this.storage, this.ui);
                    }
                } else {
                    throw new IncorrectCommandException("Please enter your commands correctly for Derek "
                            + "(e.g. todo (task)), he keeps throwing tantrums");
                }
                return this.getTask();
            }
        } catch (IncorrectCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Processes the task-related commands such as "todo", "deadline", and "event".
     * It parses the command to extract the task details and then executes the appropriate command.
     */
    public String getTask() {
        try {
            String[] words = command.split("\\s+");
            if (words[0].equals("deadline")) {
                String[] parts = command.split("/by");
                if (parts.length != 2) {
                    throw new IncorrectCommandException("Please enter your commands correctly "
                            + "for Derek (deadline (task) /by (date))");
                }
                DeadlineCommand deadlineCommand = new DeadlineCommand(this.command);
                String name = deadlineCommand.getTask();
                String[] information = name.split("/by");
                Task task = Task.deadlineTask(information[0], information[1]);
                return deadlineCommand.execute(task, this.storage, this.ui);

            } else if (words[0].equals("event")) {
                String[] parts = command.split("/from");
                String[] time = command.split("/to");
                if (parts.length != 2 && time.length != 2) {
                    throw new IncorrectCommandException("Please enter your commands correctly "
                            + "for Derek (event (task) /from (time) /to (time)");
                }
                EventCommand eventCommand = new EventCommand(this.command);
                String name = eventCommand.getTask();
                String[] information1 = name.split("/from");
                String[] information2 = information1[1].split("/to");
                Task task = Task.eventTask(information1[0], information2[0], information2[1]);
                return eventCommand.execute(task, this.storage, this.ui);
            } else if (words[0].equals("todo")) {
                TodoCommand todoCommand = new TodoCommand(this.command);
                String name = todoCommand.getTask();
                Task task = Task.toDoTask(name);
                return todoCommand.execute(task, this.storage, this.ui);
            } else {
                throw new IncorrectCommandException("Is it a todo, event, or deadline?\n"
                        + "Please enter your commands correctly for Derek (e.g. todo (task)), "
                        + "he keeps throwing tantrums");
            }
        } catch (IncorrectCommandException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please enter your date in the correct format: DD/MM/YYYY HH:MM";
        }
    }
}
