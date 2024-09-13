package derek;

import java.time.format.DateTimeParseException;
import java.util.Random;
import derek.exception.IncorrectCommandException;
import derek.task.Task;
import derek.task.TaskList;
import javafx.application.Platform;

/**
 * The {@code Ui} class handles user interaction in the application.
 * It manages input/output operations and facilitates communication between the user and the system.
 */
public class Ui {

    private static String logo = " ---      ---\n"
            + "|  #  |   |  #  |\n"
            + "  ---     ---\n"
            + "   \\            /\n"
            + "      ----\n";

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a {@code Ui} object with the specified storage and task list.
     *
     * @param storage the storage object that manages task persistence
     * @param taskList the task list that holds the tasks
     */
    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Introduces Derek and initiates user interaction to become friends.
     *
     * @return a string representing the introduction message
     */
    public String introduce() {
        return "Hello! I'm Derek! Can we be friends?\n" + logo + "\nYour response (Y/N):";
    }

    /**
     * Prompts the user to enter their name and starts the main user interaction loop.
     *
     * @return a string prompting the user to provide their name
     */
    public String getUserName() {
        return "Great! I have always wanted a friend!\nWhat do I call you?";
    }

    /**
     * Displays a welcome message and provides instructions for the user to enter commands.
     *
     * @param user the name of the user
     * @return a string providing the command instructions for Derek
     */
    public String initiateUserInteraction(String user) {
        return "\nHi! " + user
                + "! So, I guess as a friend I become your little slave!\n"
                + "What do you want me to do?\n"
                + "-------------------------------------------\n"
                + "Please enter your commands correctly for Derek (he's a little slow):\n"
                + "todo (task)\n"
                + "event (task) /from (DD/MM/YYYY HH:MM) /to (DD/MM/YYYY HH:MM))\n"
                + "deadline (task) /by (DD/MM/YYYY HH:MM)\n"
                + "mark (task number)\n"
                + "unmark (task number)\n"
                + "delete (task number)\n"
                + "find (keyword)\n"
                + "bye";
    }

    /**
     * Processes and returns the result of a user command.
     *
     * @param command the user command input
     * @return the result of the command
     * @throws IncorrectCommandException if the command is invalid
     * @throws DateTimeParseException if there is a date format error
     */
    public String processCommands(String command) throws IncorrectCommandException, DateTimeParseException {
        Parser parser = new Parser(command, this.storage, this);
        return parser.getCommand();
    }

    /**
     * Processes and returns the result of the user's consent response ("Y" or "N").
     *
     * @param command the user consent input
     * @return the result of the consent command
     * @throws IncorrectCommandException if the consent input is invalid
     */
    public String processConsent(String command) throws IncorrectCommandException {
        Parser parser = new Parser(command, this.storage, this);
        return parser.getConsent();
    }

    /**
     * Exits the application and returns an empty string.
     *
     * @return an empty string (exit signal)
     */
    public String printLeavingMessage() {
        Platform.exit();
        return "";
    }

    /**
     * Returns the list of tasks for the user.
     *
     * @return a string containing the list of tasks
     */
    public String returnList() {
        return "I think these are your tasks! Please don't leave me!!\n" + this.taskList;
    }

    /**
     * Removes a task from the task list and prints a message indicating the task was removed.
     *
     * @param task the task that was removed
     * @return a string message confirming the task has been removed
     */
    public String removeTask(Task task) {
        return "phew! that list was looooonngggg... i was getting tired of remembering it!"
                + "\n" + task.toString();
    }

    /**
     * Marks a task as completed and returns a celebration message.
     *
     * @param task the task that was completed
     * @return a string message celebrating the completion of the task
     */
    public String completeTask(Task task) {
        String celebration = generateRandomCelebration();
        return celebration + " you slayed that!" + "\n" + task.toString();
    }

    /**
     * Marks a task as incomplete and returns a message indicating the task was unmarked.
     *
     * @param task the task that was marked as incomplete
     * @return a string message indicating the task is incomplete
     */
    public String incompleteTask(Task task) {
        return "You'll get 'em next time!" + "\n" + task.toString();
    }

    /**
     * Adds a task to the task list and returns a confirmation message.
     *
     * @param task the task that was added
     * @return a string message confirming the task has been added
     */
    public String addTask(Task task) {
        String celebration = generateRandomCelebration();
        return celebration + "\n" + task + "\n";
    }

    /**
     * Prints the details of the task found by the user.
     *
     * @param task the task to be printed
     * @return a string message containing the task details
     */
    public String printTask(Task task) {
        return "Here you are!" + "\n" + task;
    }

    /**
     * Generates a random celebration message from a list of predefined messages.
     *
     * @return a random celebration message as a string
     */
    public String generateRandomCelebration() {
        String[] celebrationMessages = new String[]{
                "yay!", "woohoo!", "let's go!!!!", "great job :)", "you're on a roll!"
        };
        Random random = new Random();
        int min = 0;
        int max = celebrationMessages.length - 1;
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return celebrationMessages[randomNumber];
    }
}
