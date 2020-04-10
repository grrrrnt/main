package seedu.volant.commons.logic.commands;

import seedu.volant.commons.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Volant as requested ...";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exits the program."
            + "\nExample: " + COMMAND_WORD
            + "\nTIP:\tYou can also use the function key F1 to exit the program.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false, false);
    }

}
