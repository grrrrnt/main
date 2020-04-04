package seedu.volant.itinerary.exceptions;

import seedu.volant.commons.logic.commands.exceptions.CommandException;

/**
 * Signals that the timing of the Activity to be added already has an Activity taking place at
 * the same date and time.
 */
public class TimeClashException extends CommandException {
    public TimeClashException(String message) {
        super(message);
    }
}
