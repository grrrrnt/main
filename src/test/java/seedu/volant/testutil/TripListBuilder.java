package seedu.volant.testutil;

import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.Trip;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code TripList ab = new TripListBuilder().withPerson("John", "Doe").build();}
 */
public class TripListBuilder {

    private TripList addressBook;

    public TripListBuilder() {
        addressBook = new TripList();
    }

    public TripListBuilder(TripList addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Trip} to the {@code TripList} that we are building.
     */
    public TripListBuilder withPerson(Trip trip) {
        addressBook.addTrip(trip);
        return this;
    }

    public TripList build() {
        return addressBook;
    }
}
