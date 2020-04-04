package seedu.volant.commons.logic.commands;

import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;

/**
 * Refreshes the page to its original state.
 */
public class RefreshCommand extends Command {

    public static final String COMMAND_WORD = "rf";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Reloads the current page to its original state."
        + "\nExample: " + COMMAND_WORD
        + "\nTIP:\t You can also enter F5 to execute this command.";

    public static final String MESSAGE_SUCCESS = "The page has been refreshed!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new RefreshCommandResult(MESSAGE_SUCCESS);
    }
}
