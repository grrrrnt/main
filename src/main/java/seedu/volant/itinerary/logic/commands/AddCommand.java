package seedu.volant.itinerary.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TITLE;

import java.time.LocalDate;

import seedu.volant.commons.exceptions.DatePassedException;
import seedu.volant.commons.exceptions.DateRangeOutOfBoundsException;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.itinerary.exceptions.TimeClashException;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;

/**
 * Adds a activity to activity list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an activity to the itinerary.\n"
            + "Parameters: \n"
            + PREFIX_TITLE + "ACTIVITY_TITLE "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME \n"

            + "Example:\n" + COMMAND_WORD + " "
            + PREFIX_TITLE + "Visit World Trade Centre "
            + PREFIX_LOCATION + "New York "
            + PREFIX_DATE + "05-03-2020 "
            + PREFIX_TIME + "09:00";

    public static final String MESSAGE_SUCCESS = "New activity added: %1$s";
    public static final String MESSAGE_DUPLICATE_ITEM = "This activity already exists in your itinerary.";
    public static final String MESSAGE_OUT_OF_RANGE_BEFORE = "Date of activity is before the trip!\n"
            + "Please enter a date within the duration of the trip: %s";
    public static final String MESSAGE_OUT_OF_RANGE_AFTER = "Date of activity is after the trip!\n"
            + "Please enter a date within the duration of the trip: %s";
    public static final String MESSAGE_DATE_PASSED = "Date of activity has passed. "
            + "Please entire a current or future date.";
    public static final String MESSAGE_TIME_CLASH = "There is already another activity scheduled on %s at %s. "
            + "Try another timing.";

    private final Activity toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Trip}
     */
    public AddCommand(Activity activity) {
        requireNonNull(activity);
        toAdd = activity;
    }

    /**
     * throws a command exception if the attributes of the new activity violates any exceptions
     * @param itineraryModel Itinerary of trip
     * @throws CommandException If an the activity to be added has any logic flaws.
     */
    private void checkForExceptions(ItineraryModelManager itineraryModel) throws CommandException {
        if (itineraryModel.hasActivity(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        if (toAdd.getDate().compareTo(itineraryModel.getTrip().getDateRange().getFrom()) < 0) {
            throw new DateRangeOutOfBoundsException(String.format(MESSAGE_OUT_OF_RANGE_BEFORE,
                    itineraryModel.getTrip().getDateRange()));
        }

        if (toAdd.getDate().compareTo(itineraryModel.getTrip().getDateRange().getTo()) > 0) {
            throw new DateRangeOutOfBoundsException(String.format(MESSAGE_OUT_OF_RANGE_AFTER,
                    itineraryModel.getTrip().getDateRange()));
        }

        if (toAdd.getDate().compareTo(LocalDate.now()) < 0) {
            throw new DatePassedException(MESSAGE_DATE_PASSED);
        }

        if (itineraryModel.hasTimeClash(toAdd)) {
            throw new TimeClashException(String.format(MESSAGE_TIME_CLASH,
                    toAdd.getDate(), toAdd.getTime()));
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ItineraryModelManager itineraryModel = ((ItineraryModelManager) model);
        checkForExceptions(itineraryModel);
        itineraryModel.addActivity(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

