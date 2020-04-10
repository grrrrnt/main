package seedu.volant.commons.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.HomeModelManager;

// TODO: Complete this implementation

/**
 * Returns to HOME page from any page.
 */
public class HomeCommand extends GotoCommand {
    public static final String COMMAND_WORD = "home";

    public static final String RESULT_INVALID = "You are already on the home page.";
    public static final String RESULT_SUCCESS = "You are now on the home page.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Goes back to the HOME page.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // If in the home page
        if (model instanceof HomeModelManager) {
            throw new CommandException(RESULT_INVALID);
        }

        return new CommandResult(RESULT_SUCCESS, model);
    }
}
