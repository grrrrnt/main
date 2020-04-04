package seedu.volant.itinerary.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.volant.commons.logic.parser.ArgumentMultimap;
import seedu.volant.commons.logic.parser.ArgumentTokenizer;
import seedu.volant.commons.logic.parser.Parser;
import seedu.volant.commons.logic.parser.ParserUtil;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.itinerary.logic.commands.FindCommand;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DATE, PREFIX_TIME, PREFIX_LOCATION);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        FindCommand.FindItineraryDescriptor findItineraryDescriptor = new FindCommand.FindItineraryDescriptor();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            String titleTrimmedArgs = argMultimap.getValue(PREFIX_TITLE).get().trim();
            if (titleTrimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            String[] titleKeywords = titleTrimmedArgs.split("\\s+");
            findItineraryDescriptor.setTitle(titleKeywords);
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            String locationTrimmedArgs = argMultimap.getValue(PREFIX_LOCATION).get().trim();
            if (locationTrimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            String[] locationKeywords = locationTrimmedArgs.split("\\s+");
            findItineraryDescriptor.setLocation(locationKeywords);
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            String dateArgs = argMultimap.getValue(PREFIX_DATE).get();
            findItineraryDescriptor.setDate(ParserUtil.parseDate(dateArgs));
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            String timeArgs = argMultimap.getValue(PREFIX_TIME).get();
            findItineraryDescriptor.setTime(ParserUtil.parseTime(timeArgs));
        }

        if (!findItineraryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(findItineraryDescriptor);
    }

}
