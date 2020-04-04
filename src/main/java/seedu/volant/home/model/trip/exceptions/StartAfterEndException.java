package seedu.volant.home.model.trip.exceptions;

import seedu.volant.commons.logic.parser.exceptions.ParseException;

/**
 *
 */
public class StartAfterEndException extends ParseException {
    public StartAfterEndException(String message) {
        super(message);
    }
}
