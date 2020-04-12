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
    public static final String MESSAGE_INVALID_DATE = "Please input a valid date in DD-MM-YYYY format!";
    public static final String MESSAGE_INVALID_TIME = "Please input a valid time in HH:MM format!";
    public static final String MESSAGE_ERROR_LOADING_DATE = "Error loading date. Date storage in file manipulated.";
    public static final String MESSAGE_ERROR_LOADING_TIME = "Error loading time. Time storage in file manipulated.";
}
