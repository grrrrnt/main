package seedu.volant.ui.pages.trip;

import seedu.volant.commons.logic.commands.GotoCommand;
import seedu.volant.commons.logic.commands.HomeCommand;
import seedu.volant.ui.HelpWindow;


/**
 * Help window for the TRIP page.
 */
public class TripHelpWindow extends HelpWindow {

    public TripHelpWindow() {
        super.helpWindowHeader.setText("Help Window: TRIP Page");
        super.welcome = String.format(WELCOME_TEMP, "TRIP");
        super.commands = super.welcome
                + GotoCommand.MESSAGE_USAGE_TRIP + "\n\n"
                + HomeCommand.MESSAGE_USAGE + "\n\n"
                + COMMON_COMMANDS;
    }
}
