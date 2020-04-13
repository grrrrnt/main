package seedu.volant.home.model.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_LOCATION_CNY;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalTrips.A;
import static seedu.volant.testutil.TypicalTrips.B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.volant.home.model.trip.exceptions.DuplicateTripException;
import seedu.volant.home.model.trip.exceptions.TripNotFoundException;
import seedu.volant.testutil.TripBuilder;

public class UniqueTripListTest {

    private final UniqueTripList uniqueTripList = new UniqueTripList();

    @Test
    public void contains_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.contains(null));
    }

    @Test
    public void contains_tripNotInList_returnsFalse() {
        assertFalse(uniqueTripList.contains(A));
    }

    @Test
    public void contains_tripInList_returnsTrue() {
        uniqueTripList.add(A);
        assertTrue(uniqueTripList.contains(A));
    }

    @Test
    public void contains_tripWithSameMetaFieldsInList_returnsTrue() {
        uniqueTripList.add(A);
        Trip editedAlice = new TripBuilder(A).withLocation(VALID_LOCATION_CNY)
                .build();
        assertTrue(uniqueTripList.contains(editedAlice));
    }

    @Test
    public void add_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.add(null));
    }

    @Test
    public void add_duplicateTrip_throwsDuplicateTripException() {
        uniqueTripList.add(A);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.add(A));
    }

    @Test
    public void setTrip_nullTargetTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrip(null, A));
    }

    @Test
    public void setTrip_nullEditedTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrip(A, null));
    }

    @Test
    public void setTrip_targetTripNotInList_throwsTripNotFoundException() {
        assertThrows(TripNotFoundException.class, () -> uniqueTripList.setTrip(A, A));
    }

    @Test
    public void setTrip_editedTripIsSameTrip_success() {
        uniqueTripList.add(A);
        uniqueTripList.setTrip(A, A);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        expectedUniqueTripList.add(A);
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setTrip_editedTripHasSameIdentity_success() {
        uniqueTripList.add(A);
        Trip editedAlice = new TripBuilder(A).withLocation(VALID_LOCATION_CNY)
                .build();
        uniqueTripList.setTrip(A, editedAlice);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        expectedUniqueTripList.add(editedAlice);
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setTrip_editedTripHasDifferentIdentity_success() {
        uniqueTripList.add(A);
        uniqueTripList.setTrip(A, B);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        expectedUniqueTripList.add(B);
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setTrip_editedTripHasNonUniqueIdentity_throwsDuplicateTripException() {
        uniqueTripList.add(A);
        uniqueTripList.add(B);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.setTrip(A, B));
    }

    @Test
    public void remove_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.remove(null));
    }

    @Test
    public void remove_tripDoesNotExist_throwsTripNotFoundException() {
        assertThrows(TripNotFoundException.class, () -> uniqueTripList.remove(A));
    }

    @Test
    public void remove_existingTrip_removesTrip() {
        uniqueTripList.add(A);
        uniqueTripList.remove(A);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setTrips_nullUniqueTripList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrips((UniqueTripList) null));
    }

    @Test
    public void setTrips_uniqueTripList_replacesOwnListWithProvidedUniqueTripList() {
        uniqueTripList.add(A);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        expectedUniqueTripList.add(B);
        uniqueTripList.setTrips(expectedUniqueTripList);
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setTrips_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrips((List<Trip>) null));
    }

    @Test
    public void setTrips_list_replacesOwnListWithProvidedList() {
        uniqueTripList.add(A);
        List<Trip> tripList = Collections.singletonList(B);
        uniqueTripList.setTrips(tripList);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        expectedUniqueTripList.add(B);
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setTrips_listWithDuplicateTrips_throwsDuplicateTripException() {
        List<Trip> listWithDuplicateTrips = Arrays.asList(A, A);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.setTrips(listWithDuplicateTrips));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTripList.asUnmodifiableObservableList().remove(0));
    }
}
