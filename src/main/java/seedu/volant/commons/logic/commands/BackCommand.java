package seedu.volant.commons.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.Page.HOME;
import static seedu.volant.commons.logic.Page.ITINERARY;
import static seedu.volant.commons.logic.Page.JOURNAL;
import static seedu.volant.commons.logic.Page.TRIP;

import seedu.volant.commons.core.Messages;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.trip.model.TripModelManager;

/**
 * Goes back one page from current page.
 */
public class BackCommand extends Command {

    public static final String COMMAND_WORD = "back";

    public static final String BACK_HOMEPAGE = "You are already on the home page.";
    public static final String BACK_TRIP = "You have gone back to the home page.";
    public static final String BACK_FEATURE = "You have returned to the trip page of TRIP: %s";

    /**
     * Handles back command if command was entered from the Trip page.
     */
    private CommandResult handleBackFromTrip(Model model) {
        TripModelManager t = ((TripModelManager) model);
        CommandResult commandResult = new CommandResult(BACK_TRIP);
        commandResult.setBack();
        return commandResult;
    }

    /**
     * Handles back command if command was entered from the Itinerary page.
     */
    private CommandResult handleBackFromItinerary(Model model) {
        ItineraryModelManager t = ((ItineraryModelManager) model);
        CommandResult commandResult = new CommandResult(String.format(BACK_FEATURE,
                t.getTrip().getName().toString()), t.getTrip());
        commandResult.setBack();
        return commandResult;
    }

    /**
     * Handles back command if command was entered from the Journal page.
     */
    private CommandResult handleBackFromJournal(Model model) {
        JournalModelManager t = ((JournalModelManager) model);
        CommandResult commandResult = new CommandResult(String.format(BACK_FEATURE,
                t.getTrip().getName().toString()), t.getTrip());
        commandResult.setBack();
        return commandResult;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        CommandResult commandResult;
        Page page = model.getPage();

        // If in a TRIP page, go back to HOME page.
        if (page.equals(TRIP)) {
            return handleBackFromTrip(model);

        } else if (page.equals(ITINERARY)) { // If in the itinerary page
            return handleBackFromItinerary(model);

        } else if (page.equals(JOURNAL)) { // If in the journal page
            return handleBackFromJournal(model);

        } else if (page.equals(HOME)) { // If in the home page
            throw new CommandException(BACK_HOMEPAGE);
        } else {
            throw new CommandException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
