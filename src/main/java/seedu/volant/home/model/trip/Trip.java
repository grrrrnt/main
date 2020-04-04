package seedu.volant.home.model.trip;

import static seedu.volant.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.volant.trip.model.Itinerary;
import seedu.volant.trip.model.Journal;
import seedu.volant.trip.model.TripFeatureList;

/**
 * Represents a Trip in the location book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Trip {

    // Identity fields
    private final Name name;

    // Data fields
    private final Location location;
    private final DateRange dateRange;

    private TripFeatureList tripFeatureList;

    /**
     * Every field must be present and not null.
     */
    public Trip(Name name, Location location, DateRange dateRange) {
        requireAllNonNull(name, location, dateRange);
        this.name = name;
        this.location = location;
        this.dateRange = dateRange;

        tripFeatureList = new TripFeatureList();
    }

    public Name getName() {
        return name;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public Location getLocation() {
        return location;
    }

    public TripFeatureList getTripFeatureList() {
        return tripFeatureList;
    }

    public Journal getJournal() {
        return tripFeatureList.getJournal();
    }

    public Itinerary getItinerary() {
        return tripFeatureList.getItinerary();
    }

    public Itinerary goToItinerary() {
        return getItinerary();
    }

    public Journal goToJournal() {
        return getJournal();
    }

    /**
     * Returns true if both trips have same name.
     * TODO: Confirm this
     */
    public boolean isSameTrip(Trip otherTrip) {
        if (otherTrip == this) {
            return true;
        }

        return otherTrip != null
                && otherTrip.getName().equals(name);
    }

    /**
     * Returns true if the date range of this conflicts with date range of {@code other}.
     */
    public boolean isWithinSameDateRange(Trip other) {
        if ((other.getDateRange().getFrom().compareTo(dateRange.from) >= 0)
                && other.getDateRange().getTo().compareTo(dateRange.to) <= 0) {
            return true;
        } else if ((other.getDateRange().getFrom().compareTo(dateRange.from) < 0)
                && other.getDateRange().getTo().compareTo(dateRange.from) >= 0) {
            return true;
        } else {
            return (other.getDateRange().getFrom().compareTo(dateRange.from) >= 0)
                    && other.getDateRange().getFrom().compareTo(dateRange.to) < 0;
        }
    }

    /**
     * Returns true if both trips have the same identity and data fields.
     * This defines a stronger notion of equality between two trips.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Trip)) {
            return false;
        }

        Trip otherTrip = (Trip) other;
        return otherTrip.getName().equals(getName())
                && otherTrip.getLocation().equals(getLocation())
                && otherTrip.getDateRange().equals(getDateRange());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, location, dateRange);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nName: ")
                .append(getName())
                .append("\nLocation: ")
                .append(getLocation())
                .append("\nDate Range: ")
                .append(getDateRange());
        return builder.toString();
    }

}
