package seedu.volant.home.model.trip.exceptions;

import seedu.volant.commons.logic.parser.exceptions.ParseException;

/**
 * Exception for when user adds a Trip to the trip list with a date range that conflicts with another trip.
 */
public class StartAfterEndException extends ParseException {
    public StartAfterEndException(String message) {
        super(message);
    }
}
