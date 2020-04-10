package seedu.volant.ui.pages.home;

import seedu.volant.commons.logic.commands.GotoCommand;
import seedu.volant.home.logic.commands.AddCommand;
import seedu.volant.home.logic.commands.DeleteCommand;
import seedu.volant.home.logic.commands.EditCommand;
import seedu.volant.home.logic.commands.FindCommand;
import seedu.volant.ui.HelpWindow;

/**
 * Help window for the HOME page.
 */
public class HomeHelpWindow extends HelpWindow {

    public HomeHelpWindow() {
        super.helpWindowHeader.setText("Help Window: HOME Page");
        super.welcome = String.format(WELCOME_TEMP, "HOME");
        super.commands = welcome
                + AddCommand.MESSAGE_USAGE + "\n\n"
                + DeleteCommand.MESSAGE_USAGE + "\n\n"
                + EditCommand.MESSAGE_USAGE + "\n\n"
                + FindCommand.MESSAGE_USAGE + "\n\n"
                + GotoCommand.MESSAGE_USAGE_HOME + "\n\n"
                + COMMON_COMMANDS;
    }
}
