package seedu.volant.itinerary.model.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_LOCATION_CNY;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalActivities.A;
import static seedu.volant.testutil.TypicalActivities.B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.volant.itinerary.exceptions.DuplicateActivityException;
import seedu.volant.itinerary.model.activity.exceptions.NotFoundException;
import seedu.volant.testutil.ActivityBuilder;

public class UniqueActivityListTest {

    private final UniqueActivityList uniqueActivityList = new UniqueActivityList();

    @Test
    public void contains_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.contains(null));
    }

    @Test
    public void contains_activityNotInList_returnsFalse() {
        assertFalse(uniqueActivityList.contains(A));
    }

    @Test
    public void contains_activityInList_returnsTrue() {
        uniqueActivityList.add(A);
        assertTrue(uniqueActivityList.contains(A));
    }

    @Test
    public void contains_activityWithSameMetaFieldsInList_returnsFalse() {
        uniqueActivityList.add(A);
        Activity editedAlice = new ActivityBuilder(A).withLocation(VALID_LOCATION_CNY)
                .build();
        assertFalse(uniqueActivityList.contains(editedAlice));
    }

    @Test
    public void add_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.add(null));
    }

    @Test
    public void add_duplicateActivity_throwsDuplicateActivityException() {
        uniqueActivityList.add(A);
        assertThrows(DuplicateActivityException.class, () -> uniqueActivityList.add(A));
    }

    @Test
    public void setActivity_nullTargetActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.setActivity(null, A));
    }

    @Test
    public void setActivity_nullEditedActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.setActivity(A, null));
    }

    @Test
    public void setActivity_targetActivityNotInList_throwsNotFoundException() {
        assertThrows(NotFoundException.class, () -> uniqueActivityList.setActivity(A, A));
    }

    @Test
    public void setActivity_editedActivityIsSameActivity_success() {
        uniqueActivityList.add(A);
        uniqueActivityList.setActivity(A, A);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(A);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasSameIdentity_success() {
        uniqueActivityList.add(A);
        Activity editedAlice = new ActivityBuilder(A).withLocation(VALID_LOCATION_CNY)
                .build();
        uniqueActivityList.setActivity(A, editedAlice);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(editedAlice);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasDifferentIdentity_success() {
        uniqueActivityList.add(A);
        uniqueActivityList.setActivity(A, B);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(B);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasNonUniqueIdentity_throwsDuplicateActivityException() {
        uniqueActivityList.add(A);
        uniqueActivityList.add(B);
        assertThrows(DuplicateActivityException.class, () -> uniqueActivityList.setActivity(A, B));
    }

    @Test
    public void remove_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.remove(null));
    }

    @Test
    public void remove_activityDoesNotExist_throwsNotFoundException() {
        assertThrows(NotFoundException.class, () -> uniqueActivityList.remove(A));
    }

    @Test
    public void remove_existingActivity_removesActivity() {
        uniqueActivityList.add(A);
        uniqueActivityList.remove(A);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_nullUniqueActivityList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.setActivities((UniqueActivityList) null));
    }

    @Test
    public void setActivities_uniqueActivityList_replacesOwnListWithProvidedUniqueActivityList() {
        uniqueActivityList.add(A);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(B);
        uniqueActivityList.setActivities(expectedUniqueActivityList);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.setActivities((List<Activity>) null));
    }

    @Test
    public void setActivities_list_replacesOwnListWithProvidedList() {
        uniqueActivityList.add(A);
        List<Activity> activityList = Collections.singletonList(B);
        uniqueActivityList.setActivities(activityList);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(B);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_listWithDuplicateActivities_throwsDuplicateActivityException() {
        List<Activity> listWithDuplicateActivitys = Arrays.asList(A, A);
        assertThrows(DuplicateActivityException.class, (
            ) -> uniqueActivityList.setActivities(listWithDuplicateActivitys));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, (
            )-> uniqueActivityList.asUnmodifiableObservableList().remove(0));
    }
}
