package seedu.volant.itinerary.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TITLE;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import javafx.collections.ObservableList;
import seedu.volant.commons.core.Messages;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.util.CollectionUtil;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.itinerary.model.activity.util.DateContainsKeywordsPredicate;
import seedu.volant.itinerary.model.activity.util.LocationContainsKeywordsPredicate;
import seedu.volant.itinerary.model.activity.util.TimeContainsKeywordsPredicate;
import seedu.volant.itinerary.model.activity.util.TitleContainsKeywordsPredicate;



/**
 * Finds and lists all activities in itinerary whose fields contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds the activity identified "
            + "by the keywords input (case-insensitive) and displays "
            + "them as a list with index number."

            + "\nParameters:\t"
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "[" + PREFIX_LOCATION + "LOCATION]"

            + "\nExample:\t"
            + COMMAND_WORD + " " + PREFIX_TITLE + "visit " + PREFIX_LOCATION + "Brandenburg"

            + "\nNOTE:\t\tAt least one of the parameters must be provided.";


    public static final String MESSAGE_EMPTY_FIELD = "At least one field to find must be provided.";

    private final FindItineraryDescriptor findItineraryDescriptor;
    private TitleContainsKeywordsPredicate titlePredicate;
    private LocationContainsKeywordsPredicate locationPredicate;
    private DateContainsKeywordsPredicate datePredicate;
    private TimeContainsKeywordsPredicate timePredicate;

    public FindCommand(FindItineraryDescriptor findItineraryDescriptor) {
        this.findItineraryDescriptor = new FindItineraryDescriptor(findItineraryDescriptor);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ItineraryModelManager itineraryModel = ((ItineraryModelManager) model);
        ObservableList<Activity> currentList = itineraryModel.getFilteredActivityList();

        if (findItineraryDescriptor.getTitle() != null) {
            titlePredicate = new TitleContainsKeywordsPredicate(
                    Arrays.asList(findItineraryDescriptor.getTitle()));
            itineraryModel.updateFilteredActivityList(titlePredicate);
        }

        if (findItineraryDescriptor.getLocation() != null) {
            locationPredicate = new LocationContainsKeywordsPredicate(
                    Arrays.asList(findItineraryDescriptor.getLocation()));
            itineraryModel.updateFilteredActivityList(locationPredicate);
        }

        if (findItineraryDescriptor.getDate() != null) {
            datePredicate = new DateContainsKeywordsPredicate(
                    findItineraryDescriptor.getDate());
            itineraryModel.updateFilteredActivityList(datePredicate);
        }

        if (findItineraryDescriptor.getTime() != null) {
            timePredicate = new TimeContainsKeywordsPredicate(
                    findItineraryDescriptor.getTime());
            itineraryModel.updateFilteredActivityList(timePredicate);
        }

        if (itineraryModel.getFilteredActivityList().isEmpty()) {
            itineraryModel.updateFilteredActivityList(itineraryModel.getPredicateShowAllActivities());
            return new CommandResult(
                    String.format("No activity with these keyword(s) found!", currentList.size()));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW,
                            itineraryModel.getFilteredActivityList().size()));
        }
    }

    /**
     * Stores the details of the parameters to find the activity with.
     */
    public static class FindItineraryDescriptor {
        private String[] title = null;
        private LocalDate date;
        private LocalTime time;
        private String[] location = null;

        public FindItineraryDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public FindItineraryDescriptor(FindCommand.FindItineraryDescriptor toCopy) {
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

        public void setTitle(String[] title) {
            this.title = title;
        }

        public String[] getTitle() {
            return this.title;
        }

        public void setLocation(String[] location) {
            this.location = location;
        }

        public String[] getLocation() {
            return this.location;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }

        public LocalTime getTime() {
            return time;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindCommand.FindItineraryDescriptor)) {
                return false;
            }

            // state check
            FindCommand.FindItineraryDescriptor f = (FindCommand.FindItineraryDescriptor) other;

            return getTitle().equals(f.getTitle())
                    && getLocation().equals(f.getLocation())
                    && getDate().equals(f.getDate())
                    && getTime().equals(f.getTime());
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        // state check
        FindCommand f = (FindCommand) other;
        return findItineraryDescriptor.equals(f.findItineraryDescriptor);
    }

}
