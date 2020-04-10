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

    private final TripList addressBook = new TripList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTripList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        TripList newData = getTypicalTripList();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two trips with the same identity fields
        Trip editedAlice = new TripBuilder(A).withLocation(VALID_LOCATION_CNY)
                .build();
        List<Trip> newTrips = Arrays.asList(A, editedAlice);
        TripListStub newData = new TripListStub(newTrips);

        assertThrows(DuplicateTripException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTrip(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTrip(A));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addTrip(A);
        assertTrue(addressBook.hasTrip(A));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTrip(A);
        Trip editedAlice = new TripBuilder(A).withLocation(VALID_LOCATION_CNY)
                .build();
        assertTrue(addressBook.hasTrip(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTripList().remove(0));
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
