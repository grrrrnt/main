package seedu.volant.home.model;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.home.model.trip.UniqueTripList;

/**
 * Wraps all data at the trip level
 * Duplicates are not allowed (by .isSameTrip comparison)
 */
public class TripList implements ReadOnlyTripList {

    private final UniqueTripList trips;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        trips = new UniqueTripList();
    }

    public TripList() {

    }

    /**
     * Creates an TripList using the Trips in the {@code toBeCopied}
     */
    public TripList(ReadOnlyTripList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the trip list with {@code trips}.
     * {@code trips} must not contain duplicate trips.
     */
    public void setTrips(List<Trip> trips) {
        this.trips.setTrips(trips);
    }

    /**
     * Resets the existing data of this {@code TripList} with {@code newData}.
     */
    public void resetData(ReadOnlyTripList newData) {
        requireNonNull(newData);

        setTrips(newData.getTripList());
    }

    //// trip-level operations

    /**
     * Returns true if a trip with the same identity as {@code trip} exists in the trip list.
     */
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return trips.contains(trip);
    }

    /**
     * Returns true if a trip with date range coinciding with {@code trip} exists in the trip list.
     */
    public boolean hasClash(Trip trip) {
        requireNonNull(trip);
        return trips.clashes(trip);
    }

    /**
     * Adds a trip to the trip list.
     * The trip must not already exist in the trip list.
     */
    public void addTrip(Trip p) {
        trips.add(p);
        File file = new File("data/" + p.getName());
        file.mkdir();
    }

    /**
     * Replaces the given trip {@code target} in the list with {@code editedTrip}.
     * {@code target} must exist in the trip list.
     * The trip identity of {@code editedTrip} must not be the same as another existing trip in the trip list.
     */
    public void setTrip(Trip target, Trip editedTrip) {
        requireNonNull(editedTrip);

        trips.setTrip(target, editedTrip);
    }

    /**
     * Removes {@code key} from this {@code TripList}.
     * {@code key} must exist in the trip list.
     */
    public void removeTrip(Trip key) {
        trips.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return "" + trips.asUnmodifiableObservableList().size();
    }

    @Override
    public ObservableList<Trip> getTripList() {
        return trips.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripList // instanceof handles nulls
                && trips.equals(((TripList) other).trips));
    }

    @Override
    public int hashCode() {
        return trips.hashCode();
    }
}
