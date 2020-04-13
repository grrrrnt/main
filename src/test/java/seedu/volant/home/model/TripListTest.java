package seedu.volant.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_LOCATION_CNY;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalTrips.A;
import static seedu.volant.testutil.TypicalTrips.getTypicalTripList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.home.model.trip.exceptions.DuplicateTripException;
import seedu.volant.testutil.TripBuilder;

public class TripListTest {

    private final TripList tripList = new TripList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tripList.getTripList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tripList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTripList_replacesData() {
        TripList newData = getTypicalTripList();
        tripList.resetData(newData);
        assertEquals(newData, tripList);
    }

    @Test
    public void resetData_withDuplicateTrips_throwsDuplicateTripException() {
        // Two trips with the same identity fields
        Trip editedAlice = new TripBuilder(A).withLocation(VALID_LOCATION_CNY)
                .build();
        List<Trip> newTrips = Arrays.asList(A, editedAlice);
        TripListStub newData = new TripListStub(newTrips);

        assertThrows(DuplicateTripException.class, () -> tripList.resetData(newData));
    }

    @Test
    public void hasTrip_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tripList.hasTrip(null));
    }

    @Test
    public void hasTrip_tripNotInTripList_returnsFalse() {
        assertFalse(tripList.hasTrip(A));
    }

    @Test
    public void hasTrip_tripInTripList_returnsTrue() {
        tripList.addTrip(A);
        assertTrue(tripList.hasTrip(A));
    }

    @Test
    public void hasTrip_tripWithSameIdentityFieldsInTripList_returnsTrue() {
        tripList.addTrip(A);
        Trip editedAlice = new TripBuilder(A).withLocation(VALID_LOCATION_CNY)
                .build();
        assertTrue(tripList.hasTrip(editedAlice));
    }

    @Test
    public void getTripList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tripList.getTripList().remove(0));
    }

    /**
     * A stub ReadOnlyTripList whose trips list can violate interface constraints.
     */
    private static class TripListStub implements ReadOnlyTripList {
        private final ObservableList<Trip> trips = FXCollections.observableArrayList();

        TripListStub(Collection<Trip> trips) {
            this.trips.setAll(trips);
        }

        @Override
        public ObservableList<Trip> getTripList() {
            return trips;
        }
    }

}
