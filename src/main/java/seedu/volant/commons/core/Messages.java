package seedu.volant.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND_ITINERARY = "I do not recognize this command.\n"
            + "Here are some commands you can try:\n"
            + " add\n delete\n edit\n";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command! If you need help, please enter `help`.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TRIP_DISPLAYED_INDEX = "The trip index provided is invalid.";
    public static final String MESSAGE_TRIPS_LISTED_OVERVIEW = "%1$d trip(s) listed!";
}
