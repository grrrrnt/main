package seedu.volant.testutil;

import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATERANGE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.volant.home.logic.commands.AddCommand;
import seedu.volant.home.logic.commands.EditCommand;
import seedu.volant.home.model.trip.Trip;

/**
 * A utility class for Trip.
 */
public class TripUtil {

    /**
     * Returns an add command string for adding the {@code trip}.
     */
    public static String getAddCommand(Trip trip) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(trip);
    }

    /**
     * Returns the part of command string for the given {@code trip}'s details.
     */
    public static String getPersonDetails(Trip trip) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + trip.getName().toString() + " ");
        sb.append(PREFIX_LOCATION + trip.getLocation().value + " ");
        sb.append(PREFIX_DATERANGE + trip.getDateRange().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditTripDescriptorDetails(EditCommand.EditTripDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.value).append(" "));
        descriptor.getDateRange().ifPresent(dateRange -> sb.append(PREFIX_DATERANGE)
                .append(dateRange.value).append(" "));
        return sb.toString();
    }
}
