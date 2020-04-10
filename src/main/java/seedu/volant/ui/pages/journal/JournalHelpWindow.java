package seedu.volant.ui.pages.journal;

import seedu.volant.commons.logic.commands.BackCommand;
import seedu.volant.commons.logic.commands.HomeCommand;
import seedu.volant.journal.logic.commands.AddCommand;
import seedu.volant.journal.logic.commands.DeleteCommand;
import seedu.volant.journal.logic.commands.EditCommand;
import seedu.volant.journal.logic.commands.SortCommand;
import seedu.volant.ui.HelpWindow;

/**
 * Help window for the JOURNAL page.
 */
public class JournalHelpWindow extends HelpWindow {

    public JournalHelpWindow() {
        super.helpWindowHeader.setText("Help Window: JOURNAL Page");
        super.welcome = String.format(WELCOME_TEMP, "JOURNAL");
        super.commands = welcome
                + AddCommand.MESSAGE_USAGE + "\n\n"
                + DeleteCommand.MESSAGE_USAGE + "\n\n"
                + EditCommand.MESSAGE_USAGE + "\n\n"
                + SortCommand.MESSAGE_USAGE + "\n\n"
                + BackCommand.MESSAGE_USAGE + "\n\n"
                + HomeCommand.MESSAGE_USAGE + "\n\n"
                + COMMON_COMMANDS;
    }
}
