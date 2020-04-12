package seedu.volant.testutil;

import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.Trip;

/**
 * A utility class to help with building TripList objects.
 */
public class TripListBuilder {

    private TripList tripList;

    public TripListBuilder() {
        tripList = new TripList();
    }

    public TripListBuilder(TripList tripList) {
        this.tripList = tripList;
    }

    /**
     * Adds a new {@code Trip} to the {@code TripList} that we are building.
     */
    public TripListBuilder withTrip(Trip trip) {
        tripList.addTrip(trip);
        return this;
    }

    public TripList build() {
        return tripList;
    }
}
