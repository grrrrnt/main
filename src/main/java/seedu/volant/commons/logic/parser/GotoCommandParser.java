package seedu.volant.commons.logic.parser;

import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.volant.commons.logic.Page.HOME;
import static seedu.volant.commons.logic.Page.ITINERARY;
import static seedu.volant.commons.logic.Page.JOURNAL;
import static seedu.volant.commons.logic.Page.TRIP;

import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.logic.commands.GotoCommand;
import seedu.volant.commons.logic.commands.HomeCommand;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.home.logic.commands.GotoTripCommand;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.trip.logic.commands.GotoItineraryCommand;
import seedu.volant.trip.logic.commands.GotoJournalCommand;

/**
 * Parses input arguments and creates a new GotoCommand object
 */
public class GotoCommandParser implements Parser<GotoCommand> {

    private Trip trip;
    // initializes page to be HOME
    private Page page = HOME;

    /**
     * Constructs GotoCommandParser for goto commands to go to a specific trip
     */
    public GotoCommandParser() {}

    /**
     * Constructs GotoCommandParser for goto commands to go to trip features
     */
    public GotoCommandParser(Trip trip) {
        this.trip = trip;
        this.page = TRIP;
    }

    public void setPageToJournal() {
        this.page = JOURNAL;
    }

    /**
     * Parses argument depending on which page the command is used in.
     * @param argument
     * @throws ParseException and gives feedback to user depending on which page the user is on.
     */
    @Override
    public GotoCommand parse(String argument) throws ParseException {
        try {
            String argumentTrimmed = argument.trim();
            if (page == TRIP) {
                if (argumentTrimmed.equalsIgnoreCase("itinerary")
                        || argumentTrimmed.equalsIgnoreCase("i")) {
                    return new GotoItineraryCommand(trip.getItinerary());
                } else if (argumentTrimmed.equalsIgnoreCase("journal")
                        || argumentTrimmed.equalsIgnoreCase("j")) {
                    return new GotoJournalCommand(trip.getJournal());
                }
            }

            if (page == HOME) {
                Index index = ParserUtil.parseIndex(argument);
                return new GotoTripCommand(index);
            }

            if (page == ITINERARY || page == JOURNAL) {
                if (argumentTrimmed.equals("home")) {
                    return new HomeCommand();
                }
            }

            /* If this statement is reached, it means that the command is invalid therefore throw exception */
            throw new ParseException("");

        } catch (ParseException pE) {
            if (page == HOME) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, GotoCommand.MESSAGE_USAGE_HOME, pE));
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, GotoCommand.MESSAGE_USAGE_TRIP, pE));
            }
        }
    }

}
