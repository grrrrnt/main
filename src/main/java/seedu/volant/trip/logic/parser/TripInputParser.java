package seedu.volant.trip.logic.parser;

import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.volant.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.volant.commons.logic.parser.Parser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.volant.commons.logic.commands.BackCommand;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.ExitCommand;
import seedu.volant.commons.logic.commands.GotoCommand;
import seedu.volant.commons.logic.commands.HelpCommand;
import seedu.volant.commons.logic.commands.HomeCommand;
import seedu.volant.commons.logic.commands.RefreshCommand;
import seedu.volant.commons.logic.parser.GotoCommandParser;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.home.model.trip.Trip;

/**
 * Parses user input when on a trip page.
 */
public class TripInputParser {

    private Trip trip;

    /**
     * Constructs parser and initialises parser with trip to operate commands on.
     */
    public TripInputParser(Trip trip) {
        this.trip = trip;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");

        switch(commandWord) {

        case GotoCommand.COMMAND_WORD:
            return new GotoCommandParser(trip).parse(arguments);

        case BackCommand.COMMAND_WORD:
            return new BackCommand();

        case HomeCommand.COMMAND_WORD:
            return new HomeCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RefreshCommand.COMMAND_WORD:
            return new RefreshCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        }
    }
}
