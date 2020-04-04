package seedu.volant.journal.logic.parser;

import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_FEELING;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_WEATHER;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.volant.commons.logic.parser.ArgumentMultimap;
import seedu.volant.commons.logic.parser.ArgumentTokenizer;
import seedu.volant.commons.logic.parser.Parser;
import seedu.volant.commons.logic.parser.ParserUtil;
import seedu.volant.commons.logic.parser.Prefix;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.home.model.trip.Location;
import seedu.volant.journal.exceptions.ContentTooLongException;
import seedu.volant.journal.logic.commands.AddCommand;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.Feeling;
import seedu.volant.journal.model.Weather;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException, ContentTooLongException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_TIME, PREFIX_LOCATION,
                        PREFIX_FEELING, PREFIX_WEATHER, PREFIX_TEXT);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_TIME, PREFIX_TEXT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        LocalTime time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        Entry entry = new Entry(date, time);

        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
            entry.setLocation(location);
        }
        if (argMultimap.getValue(PREFIX_FEELING).isPresent()) {
            Feeling feeling = ParserUtil.parseFeeling(argMultimap.getValue(PREFIX_FEELING).get());
            entry.setFeeling(feeling);
        }
        if (argMultimap.getValue(PREFIX_WEATHER).isPresent()) {
            Weather weather = ParserUtil.parseWeather(argMultimap.getValue(PREFIX_WEATHER).get());
            entry.setWeather(weather);
        }
        if (argMultimap.getValue(PREFIX_TEXT).isPresent()) {
            String text = ParserUtil.parseText(argMultimap.getValue(PREFIX_TEXT).get());
            entry.setText(text);
        }

        return new AddCommand(entry);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

