package seedu.volant.ui.pages.itinerary;

import seedu.volant.commons.logic.commands.BackCommand;
import seedu.volant.commons.logic.commands.HomeCommand;
import seedu.volant.itinerary.logic.commands.AddCommand;
import seedu.volant.itinerary.logic.commands.DeleteCommand;
import seedu.volant.itinerary.logic.commands.EditCommand;
import seedu.volant.itinerary.logic.commands.FindCommand;
import seedu.volant.ui.HelpWindow;

/**
 * Help window for the ITINERARY page.
 */
public class ItineraryHelpWindow extends HelpWindow {

    public ItineraryHelpWindow() {
        super.helpWindowHeader.setText("Help Window: ITINERARY Page");
        super.welcome = String.format(WELCOME_TEMP, "ITINERARY");
        super.commands = welcome
                    + AddCommand.MESSAGE_USAGE + "\n\n"
                    + DeleteCommand.MESSAGE_USAGE + "\n\n"
                    + EditCommand.MESSAGE_USAGE + "\n\n"
                    + FindCommand.MESSAGE_USAGE + "\n\n"
                    + BackCommand.MESSAGE_USAGE + "\n\n"
                    + HomeCommand.MESSAGE_USAGE + "\n\n"
                    + COMMON_COMMANDS;
    }
}
