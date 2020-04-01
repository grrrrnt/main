package seedu.volant.commons.logic.commands;

/**
 * Represents a group of command objects that help to navigate through application.
 */
public abstract class GotoCommand extends Command {

    public static final String COMMAND_WORD = "goto";

    public static final String MESSAGE_USAGE_HOME = COMMAND_WORD
            + ": Goes to a trip in the trip list. \n"
            + "Parameters:\tINDEX (must be a positive integer)\n"
            + "Example:\t\t" + COMMAND_WORD + " 1";

    public static final String MESSAGE_USAGE_TRIP = COMMAND_WORD
            + ": Goes to a feature in a trip.. \n"
            + "Parameters:\tTRIP_FEATURE (itinerary, journal)\n"
            + "Example:\t\t" + COMMAND_WORD + " itinerary\n"
            + "Tip:\t" + COMMAND_WORD + " i and " + COMMAND_WORD + " j work as well.";

}
