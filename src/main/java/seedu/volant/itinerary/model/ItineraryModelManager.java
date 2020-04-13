package seedu.volant.itinerary.model;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.Page.ITINERARY;
import static seedu.volant.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.trip.DateRange;
import seedu.volant.home.model.trip.Location;
import seedu.volant.home.model.trip.Name;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.itinerary.model.activity.util.DateTimeComparator;
import seedu.volant.trip.model.Itinerary;
import seedu.volant.trip.model.TripFeature;

/**
 * Handles in app memory when user is in an ITINERARY page.
 */
public class ItineraryModelManager implements Model {

    private final Predicate<Activity> predicateShowAllActivities = unused -> true;
    private final Trip trip;
    private final Itinerary itinerary;
    private final UserPrefs userPrefs;
    private final Page page = ITINERARY;
    private ActivityList activityList;
    private final FilteredList<Activity> filteredActivities;

    /**
     * Constructs an ItineraryModelManager that helps to keep track of in application memory.
     * @param trip keeps track of trip that itinerary list is in from.
     */
    public ItineraryModelManager(Trip trip, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(trip, userPrefs);

        LOGGER.fine("You are now in the ITINERARY page of TRIP: " + trip + ".");

        this.trip = trip;
        this.itinerary = trip.getTripFeatureList().getItinerary();
        this.userPrefs = new UserPrefs(userPrefs);
        this.activityList = itinerary.getActivityList();
        this.filteredActivities = new FilteredList<>(this.activityList.getActivityList());

    }

    public ItineraryModelManager() {
        Name tripName = new Name("Berlin berlin");
        Location tripLocation = new Location("Berlin, Germany");
        DateRange tripDateRange = new DateRange(LocalDate.parse("2022-06-10"), LocalDate.parse("2022-06-21"));

        this.trip = new Trip(tripName, tripLocation, tripDateRange);
        this.itinerary = trip.getTripFeatureList().getItinerary();
        this.userPrefs = new UserPrefs();
        this.activityList = itinerary.getActivityList();
        this.filteredActivities = new FilteredList<>(this.activityList.getActivityList());
    }


    //==========ActivityList============================================================================

    /**
     * Checks if activity list contains activity.
     * @param activity Activity to be checked.
     * @return True if activity list contains activity.
     */
    public boolean hasActivity(Activity activity) {
        requireNonNull(activity);
        return activityList.hasActivity(activity);
    }

    /**
     * Checks if activity list has any activity who's time and date clashes with the activity
     * to be added.
     * @param activity Activity to be checked.
     * @return True if the activity to be added has a time and date clash with the current
     * activities in the itinerary.
     */
    public boolean hasTimeClash(Activity activity) {
        requireNonNull(activity);
        return activityList.hasTimeClash(activity);
    }

    /**
     * Adds target activity to activity list
     * @param target Activity to be added
     */
    public void addActivity(Activity target) {
        activityList.addActivity(target);
        updateFilteredActivityList(predicateShowAllActivities);
    }

    /**
     * Deletes target activity in the activity list
     * @param target Activity to be deleted
     */
    public void deleteActivity(Activity target) {
        activityList.removeActivity(target);
        updateFilteredActivityList(predicateShowAllActivities);
    }

    /**
     * Replaces target trip with editedTrip in the trip list within model.
     */
    public void setActivity(Activity target, Activity editedTrip) {
        requireAllNonNull(target, editedTrip);
        activityList.setActivities(target, editedTrip);
    }

    public void setActivityList(ActivityList activityList) {
        requireAllNonNull(activityList);
        this.activityList = activityList;
    }

    public Predicate<Activity> getPredicateShowAllActivities() {
        return predicateShowAllActivities;
    }

    @Override
    public Page getPage() {
        return page;
    }

    public Trip getTrip() {
        return trip;
    }

    public TripFeature getItinerary() {
        return itinerary;
    }

    public ActivityList getActivityList() {
        return activityList;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Trip} backed by the internal list of
     * {@code versionedAddressBook}
     */

    public ObservableList<Activity> getFilteredActivityList() {
        return filteredActivities.sorted(new DateTimeComparator());
    }

    /**
     * Updates the filtered Activity list according to the given predicate.
     */
    public void updateFilteredActivityList(Predicate<Activity> predicate) {
        requireNonNull(predicate);
        filteredActivities.setPredicate(predicate);
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getVolantFilePath() {
        return userPrefs.getVolantFilePath();
    }

    @Override
    public void setVolantFilePath(Path volantFilePath) {
        requireNonNull(volantFilePath);
        userPrefs.setVolantFilePath(volantFilePath);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ItineraryModelManager)) {
            return false;
        }

        // state check
        ItineraryModelManager other = (ItineraryModelManager) obj;
        return trip.equals(other.trip)
                && userPrefs.equals(other.userPrefs)
                && filteredActivities.equals(other.filteredActivities);
    }
}
