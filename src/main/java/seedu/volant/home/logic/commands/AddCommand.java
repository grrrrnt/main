package seedu.volant.home.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATERANGE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.itinerary.exceptions.TimeClashException;

/**
 * Adds a trip to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a trip to the trip list. "
            + "\nParameters:\t"
            + PREFIX_NAME + "NAME "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_DATERANGE + "DATE RANGE"

            + "\nExample:\t"
            + COMMAND_WORD + " "
            + PREFIX_NAME + "Bali 2020 "
            + PREFIX_LOCATION + "Bali "
            + PREFIX_DATERANGE + "02-01-2020 to 02-05-2020 ";

    public static final String MESSAGE_SUCCESS = "New trip added: %1$s";
    public static final String MESSAGE_DUPLICATE_TRIP = "This trip already exists in the trip list";

    private final Trip toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Trip}
     */
    public AddCommand(Trip trip) {
        requireNonNull(trip);
        toAdd = trip;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        HomeModelManager homeModel = ((HomeModelManager) model);

        if (homeModel.hasTrip(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRIP);
        }

        if (homeModel.hasClash(toAdd)) {
            throw new TimeClashException(String.format("Sorry, another trip is going on between %s.\n "
                            + "You can try editing:\n"
                            + "     that entry's date range\n     your new trip's date range.",
                    toAdd.getDateRange()));
        }

        homeModel.addTrip(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
