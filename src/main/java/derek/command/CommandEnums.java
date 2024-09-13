package derek.command;

/**
 * The {@code CommandEnums} enum defines the valid commands that the Derek bot can process.
 * Each enum constant represents a different type of command that the user can input.
 * These commands are used in the {@code Parser} class to identify and execute the appropriate actions.
 */
public enum CommandEnums {

    /**
     * The {@code BYE} command is used to terminate the program.
     */
    BYE,

    /**
     * The {@code LIST} command is used to display all tasks currently stored in the task list.
     */
    LIST,

    /**
     * The {@code DEADLINE} command is used to add a new deadline task to the task list.
     * The task must include a description and a deadline time.
     */
    DEADLINE,

    /**
     * The {@code TODO} command is used to add a new to-do task to the task list.
     * The task only requires a description.
     */
    TODO,

    /**
     * The {@code EVENT} command is used to add a new event task to the task list.
     * The task must include a description, start time, and end time.
     */
    EVENT,

    /**
     * The {@code Y} command is used to indicate a positive response (Yes) in the bot's interaction with the user.
     */
    Y,

    /**
     * The {@code N} command is used to indicate a negative response (No) in the bot's interaction with the user.
     */
    N,

    /**
     * The {@code DELETE} command is used to remove a specific task from the task list.
     * The task is identified by its index in the list.
     */
    DELETE,

    /**
     * The {@code MARK} command is used to mark a task as completed.
     * The task is identified by its index in the list.
     */
    MARK,

    /**
     * The {@code UNMARK} command is used to mark a task as incomplete.
     * The task is identified by its index in the list.
     */
    UNMARK,

    /**
     * The {@code FIND} command is used to search for tasks in the task list based on a keyword or phrase.
     */
    FIND,
    COMPLETED
}
