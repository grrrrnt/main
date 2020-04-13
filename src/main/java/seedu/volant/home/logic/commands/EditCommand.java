package seedu.volant.home.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATERANGE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_NAME;

import java.io.File;
import java.util.List;
import java.util.Optional;

import seedu.volant.commons.core.Messages;
import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.util.CollectionUtil;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.trip.DateRange;
import seedu.volant.home.model.trip.Location;
import seedu.volant.home.model.trip.Name;
import seedu.volant.home.model.trip.Trip;

/**
 * Edits the details of an existing trip in the location book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the trip identified "
            + "by the index number used in the displayed trip list. "
            + "Existing values will be overwritten by the input values."
            + "\nParameters:\t"
            + "INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_DATERANGE + "DATE_RANGE]"

            + "\nExample:\t"
            + COMMAND_WORD + " 1 " + PREFIX_NAME + "Bali Trip 2020"

            + "\nNOTE:\n"
            + "+ INDEX must be a positive integer within range of trip list size.\n"
            + "+ At least one of the parameters must be provided.";

    public static final String MESSAGE_EDIT_TRIP_SUCCESS = "Edited Trip: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TRIP = "This trip already exists in the location book.";

    private final Index index;
    private final EditTripDescriptor editTripDescriptor;

    /**
     * @param index of the trip in the filtered trip list to edit
     * @param editTripDescriptor details to edit the trip with
     */
    public EditCommand(Index index, EditTripDescriptor editTripDescriptor) {
        requireNonNull(index);
        requireNonNull(editTripDescriptor);

        this.index = index;
        this.editTripDescriptor = new EditTripDescriptor(editTripDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        HomeModelManager homeModel = ((HomeModelManager) model);
        List<Trip> lastShownList = homeModel.getFilteredTripList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
        }

        Trip tripToEdit = lastShownList.get(index.getZeroBased());
        Trip editedTrip = createEditedPerson(tripToEdit, editTripDescriptor);

        if (!tripToEdit.isSameTrip(editedTrip) && homeModel.hasTrip(editedTrip)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRIP);
        }

        if (!tripToEdit.getName().equals(editedTrip.getName())) {
            File oldDir = new File("data/" + tripToEdit.getName());
            File newDir = new File("data/" + editedTrip.getName());
            oldDir.renameTo(newDir);
        }

        homeModel.setTrip(tripToEdit, editedTrip);
        homeModel.updateFilteredTripList(homeModel.getPredicateShowAllTrips());
        return new CommandResult(String.format(MESSAGE_EDIT_TRIP_SUCCESS, editedTrip));
    }

    /**
     * Creates and returns a {@code Trip} with the details of {@code tripToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Trip createEditedPerson(Trip tripToEdit, EditTripDescriptor editPersonDescriptor) {
        assert tripToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(tripToEdit.getName());
        Location updatedLocation = editPersonDescriptor.getLocation().orElse(tripToEdit.getLocation());
        DateRange updatedDateRange = editPersonDescriptor.getDateRange().orElse(tripToEdit.getDateRange());

        return new Trip(updatedName, updatedLocation, updatedDateRange);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editTripDescriptor.equals(e.editTripDescriptor);
    }

    /**
     * Stores the details to edit the trip with. Each non-empty field value will replace the
     * corresponding field value of the trip.
     */
    public static class EditTripDescriptor {
        private Name name;
        private Location location;
        private DateRange dateRange;

        public EditTripDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTripDescriptor(EditTripDescriptor toCopy) {
            setName(toCopy.name);
            setLocation(toCopy.location);
            setDateRange(toCopy.dateRange);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, location, dateRange);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setDateRange(DateRange dateRange) {
            this.dateRange = dateRange;
        }

        public Optional<DateRange> getDateRange() {
            return Optional.ofNullable(dateRange);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTripDescriptor)) {
                return false;
            }

            // state check
            EditTripDescriptor e = (EditTripDescriptor) other;

            return getName().equals(e.getName())
                    && getLocation().equals(e.getLocation())
                    && getDateRange().equals(e.getDateRange());
        }

    }
}
