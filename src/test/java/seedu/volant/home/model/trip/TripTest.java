package seedu.volant.home.model.trip;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.volant.testutil.TripBuilder;

public class TripTest {

    @Test
    public void isSameTrip() {
        Trip trip = new TripBuilder().build();
        // same object -> returns true
        assertTrue(trip.isSameTrip(trip));

        // null -> returns false
        assertFalse(trip.isSameTrip(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Trip trip = new TripBuilder().build();
        Trip tripCopy = new TripBuilder().build();
        assertTrue(trip.equals(tripCopy));

        // same object -> returns true
        assertTrue(trip.equals(trip));

        // null -> returns false
        assertFalse(trip.equals(null));

        // different type -> returns false
        assertFalse(trip.equals(5));

        // different name -> returns false
        Trip editedTrip = new TripBuilder().withName("wot").build();
        assertFalse(trip.equals(editedTrip));

        // different location -> returns false
        editedTrip = new TripBuilder().withLocation("Singapore").build();
        assertFalse(trip.equals(editedTrip));

        // different date range -> returns false
        editedTrip = new TripBuilder().withDateRange("2010-10-10", "2010-10-15").build();
        assertFalse(trip.equals(editedTrip));

    }
}
