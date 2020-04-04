package seedu.volant.commons.exceptions;

import seedu.volant.commons.logic.commands.exceptions.CommandException;

/**
 * Represents an error when a activity outside the trip's date-range is added.
 */
public class DateRangeOutOfBoundsException extends CommandException {
    public DateRangeOutOfBoundsException(String message) {
        super(message);
    }
}
