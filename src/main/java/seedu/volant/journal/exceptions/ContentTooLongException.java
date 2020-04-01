package seedu.volant.journal.exceptions;

import seedu.volant.commons.logic.parser.exceptions.ParseException;

/**
 * Represents error when content of a journal entry is greater than 280 characters.
 */
public class ContentTooLongException extends ParseException {
    public ContentTooLongException(int contentOverLimit) {
        super("I'm sorry, journal entry content has to be 280 characters or less :( \n"
                + "You are currently " + contentOverLimit + " characters over the limit.");
    }
}
