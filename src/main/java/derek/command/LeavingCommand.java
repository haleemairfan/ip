package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;

/**
 * The {@code LeavingCommand} class handles the user's command to exit the application.
 * It extends the {@code Command} class and executes the command to store tasks and display the exit message.
 */
public class LeavingCommand extends Command {

    private Ui ui;

    /**
     * Constructs a {@code LeavingCommand} with the specified user command, storage, and UI.
     *
     * @param command the user command input
     * @param storage the storage object for saving tasks before exit
     * @param ui the UI object for interacting with the user
     */
    public LeavingCommand(String command, Storage storage, Ui ui) {
        super(command, storage);
        this.ui = ui;
    }

    /**
     * Executes the {@code LeavingCommand} by storing the task list in the file
     * and returning the exit message to the user.
     *
     * @return a message indicating that the user is exiting the application
     * @throws IncorrectCommandException if there is an issue in executing the command
     */
    @Override
    public String execute() throws IncorrectCommandException {
        Storage storage = this.getStorage();
        storeInFile(storage);
        return this.ui.printLeavingMessage();
    }

    /**
     * Stores the current task list into the file.
     *
     * @param storage the storage object to store tasks
     */
    public void storeInFile(Storage storage) {
        storage.storeInFile();
    }
}
