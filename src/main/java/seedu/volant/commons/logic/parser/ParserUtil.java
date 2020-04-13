package seedu.volant.commons.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.volant.commons.core.Messages;
import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.commons.util.StringUtil;
import seedu.volant.home.model.trip.DateRange;
import seedu.volant.home.model.trip.Location;
import seedu.volant.home.model.trip.Name;
import seedu.volant.home.model.trip.exceptions.StartAfterEndException;
import seedu.volant.itinerary.model.activity.Title;
import seedu.volant.journal.exceptions.ContentTooLongException;
import seedu.volant.journal.model.Feeling;
import seedu.volant.journal.model.Weather;
import seedu.volant.journal.model.util.SortType;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "You seem to have input an invalid index.\n"
            + "Indexes can be found on the bottom right of a trip/activity/entry :)";
    private static final String MESSAGE_START_AFTER_END = "Hey, your trip start date: %s, occurs"
            + " after your trip end date: %s.\nRevise the dates and try again!";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String address} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedAddress = location.trim();
        if (!Location.isValidLocation(trimmedAddress)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedAddress);
    }

    /**
     * Parses a {@code String dateRange} int a {@code DateRange}.
     * Leading an trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateRange} is invalid.
     */
    public static DateRange parseDateRange(String dateRange) throws ParseException {
        requireNonNull(dateRange);
        String trimmedDateRange = dateRange.trim();

        if (!DateRange.isValidDateRange(trimmedDateRange)) {
            throw new ParseException(DateRange.MESSAGE_CONSTRAINTS);
        }
        Integer[] dateFields = getDateFields(trimmedDateRange);
        LocalDate parsedFromDate;
        LocalDate parsedToDate;
        try {
            parsedFromDate = LocalDate.of(dateFields[2], dateFields[1], dateFields[0]);
            parsedToDate = LocalDate.of(dateFields[5], dateFields[4], dateFields[3]);
            if (parsedFromDate.compareTo(parsedToDate) > 0) {
                throw new StartAfterEndException(String.format(MESSAGE_START_AFTER_END,
                        parsedFromDate, parsedToDate));
            }
        } catch (StartAfterEndException e) {
            throw new StartAfterEndException(e.getMessage());
        } catch (Exception e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE);
        }
        return new DateRange(parsedFromDate, parsedToDate);
    }

    private static Integer[] getDateFields(String dateRange) {
        String[] strFields = dateRange.strip().split("-| to ");
        Integer[] dateFields = new Integer[6];
        for (int i = 0; i < strFields.length; i++) {
            dateFields[i] = Integer.valueOf(strFields[i]);
        }
        return dateFields;
    }

    /**
     * Parses {@code String s} into title
     * @param title String to be parsed
     * @return title of activity.
     */
    public static Title parseTitle(String title) throws ParseException {
        if (title.isEmpty()) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(title);
    }
    /**
     * Parses a {@code String feeling} into a {@code Feeling}.
     * @param feeling String containing feeling described by user.
     * @return corresponding Feeling after parsing
     * @throws ParseException if given string does not correspond to an existing feeling
     */
    public static Feeling parseFeeling(String feeling) throws ParseException {
        requireNonNull(feeling);
        String trimmedCappedFeeling = feeling.trim().toUpperCase();
        Feeling parsedFeeling;
        try {
            parsedFeeling = Feeling.valueOf(trimmedCappedFeeling);
        } catch (Exception e) {
            throw new ParseException(Feeling.MESSAGE_CONSTRAINTS);
        }
        return parsedFeeling;
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String[] dateFields = date.strip().split("-", 3);
        LocalDate parsedDate;
        try {
            int day = Integer.parseInt(dateFields[0]);
            int month = Integer.parseInt(dateFields[1]);
            int year = Integer.parseInt(dateFields[2]);
            parsedDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE);
        }
        return parsedDate;
    }

    /**
     * Parses {@code String time} into a {@code LocalTime}.
     */
    public static LocalTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String[] timeFields = time.strip().split(":", 2);
        LocalTime parsedTime;
        try {
            int hour = Integer.parseInt(timeFields[0]);
            int minute = Integer.parseInt(timeFields[1]);
            parsedTime = LocalTime.of(hour, minute);
        } catch (Exception e) {
            throw new ParseException(Messages.MESSAGE_INVALID_TIME);
        }
        return parsedTime;
    }

    /**
     * Parses {@code String weather} into a {@code Weather}.
     */
    public static Weather parseWeather(String weather) throws ParseException {
        requireNonNull(weather);
        String trimmedCappedWeather = weather.trim().toUpperCase();
        Weather parsedWeather;
        try {
            parsedWeather = Weather.valueOf(trimmedCappedWeather);
        } catch (Exception e) {
            throw new ParseException(Weather.MESSAGE_CONSTRAINTS);
        }
        return parsedWeather;
    }

    /**
     * Parses {@code String text} into a formatted {@code String}.
     */
    public static String parseText(String text) throws ContentTooLongException {
        requireNonNull(text);
        int contentLength = text.trim().length();
        if (contentLength > 280) {
            int charactersOverLimit = contentLength - 280;
            throw new ContentTooLongException(charactersOverLimit);
        }
        return text.trim();
    }

    /**
     * Parses {@code String sortType} into a {@code SortType}.
     */
    public static SortType parseSortType(String sortType) throws ParseException {
        requireNonNull(sortType);
        try {
            String formattedSortType = sortType.trim().toLowerCase().substring(0, 1);
            switch (formattedSortType) {
            case "o":
                return SortType.OLD;
            case "n":
                return SortType.NEW;
            case "l":
                return SortType.LOCATION;
            case "f":
                return SortType.FEELING;
            default:
                throw new ParseException(SortType.MESSAGE_CONSTRAINTS);
            }
        } catch (Exception e) {
            throw new ParseException(SortType.MESSAGE_CONSTRAINTS);
        }
    }
}
