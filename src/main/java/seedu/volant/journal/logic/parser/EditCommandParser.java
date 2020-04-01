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

import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.parser.ArgumentMultimap;
import seedu.volant.commons.logic.parser.ArgumentTokenizer;
import seedu.volant.commons.logic.parser.Parser;
import seedu.volant.commons.logic.parser.ParserUtil;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.home.model.trip.Location;
import seedu.volant.journal.exceptions.ContentTooLongException;
import seedu.volant.journal.logic.commands.EditCommand;
import seedu.volant.journal.model.entry.Feeling;
import seedu.volant.journal.model.entry.Weather;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        Index index;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_TIME, PREFIX_LOCATION,
                         PREFIX_FEELING, PREFIX_WEATHER, PREFIX_TEXT);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditEntryDescriptor editEntryDescriptor = new EditCommand.EditEntryDescriptor();

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
            editEntryDescriptor.setDate(date);
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            LocalTime time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
            editEntryDescriptor.setTime(time);
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
            editEntryDescriptor.setLocation(location);
        }
        if (argMultimap.getValue(PREFIX_FEELING).isPresent()) {
            Feeling feeling = ParserUtil.parseFeeling(argMultimap.getValue(PREFIX_FEELING).get());
            editEntryDescriptor.setFeeling(feeling);
        }
        if (argMultimap.getValue(PREFIX_WEATHER).isPresent()) {
            Weather weather = ParserUtil.parseWeather(argMultimap.getValue(PREFIX_WEATHER).get());
            editEntryDescriptor.setWeather(weather);
        }
        if (argMultimap.getValue(PREFIX_TEXT).isPresent()) {
            try {
                String text = ParserUtil.parseText(argMultimap.getValue(PREFIX_TEXT).get());
                editEntryDescriptor.setText(text);
            } catch (ContentTooLongException e) {
                e.getMessage();
            }
        }
        return new EditCommand(index, editEntryDescriptor);
    }
}
