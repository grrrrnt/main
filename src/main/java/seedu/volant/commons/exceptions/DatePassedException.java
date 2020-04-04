package seedu.volant.commons.exceptions;

import seedu.volant.commons.logic.commands.exceptions.CommandException;

/**
 * Represents an error when a activity to be done is set for a past date.
 */
public class DatePassedException extends CommandException {
    public DatePassedException(String message) {
        super(message);
    }
}
