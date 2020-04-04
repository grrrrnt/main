package seedu.volant.itinerary.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TITLE;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.volant.commons.core.Messages;
import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.util.CollectionUtil;
import seedu.volant.home.model.trip.Location;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.itinerary.model.activity.Title;

/**
 * Edits the details of an existing activity in the itinerary.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the activity identified "
            + "by the index number used in the displayed activity list. "
            + "Existing values will be overwritten by the input values."

            + "\nParameters:\t"
            + "INDEX "
            + "[" + PREFIX_TITLE + "ACTIVITY_TITLE] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "[" + PREFIX_LOCATION + "LOCATION]"

            + "\nExample:\t"
            + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + " Hang out with the boys."

            + "\nNOTE:\n"
            + "+ INDEX has to be a positive integer within range of the itinerary size.\n"
            + "+ At least one of the parameters must be provided.";

    public static final String MESSAGE_EDIT_ITINERARY_SUCCESS = "Edited activity: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ACTIVITY = "This activity already exists in the itinerary.";

    private final Index index;
    private final EditItineraryDescriptor editItineraryDescriptor;

    /**
     * @param index of the trip in the filtered trip list to edit
     * @param editItineraryDescriptor details to edit the trip with
     */
    public EditCommand(Index index, EditItineraryDescriptor editItineraryDescriptor) {
        requireNonNull(index);
        requireNonNull(editItineraryDescriptor);

        this.index = index;
        this.editItineraryDescriptor = new EditItineraryDescriptor(editItineraryDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ItineraryModelManager itineraryModel = ((ItineraryModelManager) model);
        List<Activity> lastShownList = itineraryModel.getFilteredActivityList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
        }

        Activity activityToEdit = lastShownList.get(index.getZeroBased());
        Activity editedActivity = createEditedActivity(activityToEdit, editItineraryDescriptor);

        if (!activityToEdit.equals(editedActivity) && itineraryModel.hasActivity(editedActivity)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACTIVITY);
        }

        itineraryModel.setActivity(activityToEdit, editedActivity);
        itineraryModel.updateFilteredActivityList(itineraryModel.getPredicateShowAllActivities());
        return new CommandResult(String.format(MESSAGE_EDIT_ITINERARY_SUCCESS, editedActivity));
    }

    /**
     * Creates and returns a {@code Activity}
     * with the details of {@code activityToEdit}
     * edited with {@code editItineraryDescriptor}.
     */
    private static Activity createEditedActivity(Activity activityToEdit,
                                                 EditItineraryDescriptor editItineraryDescriptor) {
        assert activityToEdit != null;

        Title updatedTitle = editItineraryDescriptor.getTitle().orElse(activityToEdit.getTitle());
        Location updatedLocation = editItineraryDescriptor.getLocation().orElse(activityToEdit.getLocation());
        LocalDate updatedDate = editItineraryDescriptor.getDate().orElse(activityToEdit.getDate());
        LocalTime updatedTime = editItineraryDescriptor.getTime().orElse(activityToEdit.getTime());

        return new Activity(updatedTitle, updatedDate, updatedTime, updatedLocation);
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
                && editItineraryDescriptor.equals(e.editItineraryDescriptor);
    }

    /**
     * Stores the details to edit the trip with. Each non-empty field value will replace the
     * corresponding field value of the trip.
     */
    public static class EditItineraryDescriptor {
        private Title title;
        private LocalDate date;
        private LocalTime time;
        private Location location;

        public EditItineraryDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditItineraryDescriptor(EditItineraryDescriptor toCopy) {
            setTitle(toCopy.title);
            setDate(toCopy.date);
            setTime(toCopy.time);
            setLocation(toCopy.location);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, date, time, location);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }

        public Optional<LocalTime> getTime() {
            return Optional.ofNullable(time);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditItineraryDescriptor)) {
                return false;
            }

            // state check
            EditItineraryDescriptor e = (EditItineraryDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getLocation().equals(e.getLocation())
                    && getDate().equals(e.getDate())
                    && getTime().equals(e.getTime());
        }

    }
}
