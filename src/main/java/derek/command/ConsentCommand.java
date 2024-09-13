package derek.command;
import derek.Storage;
import derek.Ui;

/**
 * The {@code ConsentCommand} class handles user consent responses and interacts with the user interface
 * to fetch user details such as the username.
 */
public class ConsentCommand extends Command {


    /**
     * Constructs a {@code ConsentCommand} with the specified user command and UI.
     *
     * @param command the user command input
     * @param ui the UI object for interacting with the user
     * @param storage the storage object for accessing user-related data
     */
    public ConsentCommand(String command, Ui ui, Storage storage) {
        super(command, storage, ui);
    }

    /**
     * Executes the {@code ConsentCommand} by retrieving and returning the user's name.
     *
     * @return the user's name
     */
    @Override
    public String execute() {
        Ui ui = this.getUi();
        return ui.getUserName();
    }
}
