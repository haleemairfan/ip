package derek.command;
import derek.Storage;
import derek.Ui;

/**
 * The {@code DeclineCommand} class handles the user's decline response.
 * It extends the {@code Command} class and interacts with the UI to print a leaving message.
 */
public class DeclineCommand extends Command {

    /**
     * Constructs a {@code DeclineCommand} with the specified command, UI, and storage.
     *
     * @param command the user command input
     * @param ui the UI object for interacting with the user
     * @param storage the storage object for accessing necessary data
     */
    public DeclineCommand(String command, Ui ui, Storage storage) {
        super(command, storage, ui);
    }

    /**
     * Executes the {@code DeclineCommand} by returning the leaving message from the UI.
     *
     * @return the leaving message
     */
    @Override
    public String execute() {
        Ui ui = this.getUi();
        return ui.printLeavingMessage();
    }
}
