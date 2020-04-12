package seedu.volant.itinerary.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_LOCATION_CNY;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalActivities.A;
import static seedu.volant.testutil.TypicalActivities.getTypicalActivityList;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.testutil.ActivityBuilder;

public class ActivityListTest {

    private final ActivityList activityList = new ActivityList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), activityList.getActivityList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activityList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyActivityList_replacesData() {
        ActivityList newData = getTypicalActivityList();
        activityList.resetData(newData);
        assertEquals(newData, activityList);
    }

    @Test
    public void hasActivity_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activityList.hasActivity(null));
    }

    @Test
    public void hasActivity_activityNotInactivityList_returnsFalse() {
        assertFalse(activityList.hasActivity(A));
    }

    @Test
    public void hasActivity_activityInactivityList_returnsTrue() {
        activityList.addActivity(A);
        assertTrue(activityList.hasActivity(A));
    }

    @Test
    public void hasActivity_activityWithSameIdentityFieldsInactivityList_returnsFalse() {
        activityList.addActivity(A);
        Activity editedActivity = new ActivityBuilder(A).withLocation(VALID_LOCATION_CNY)
                .build();
        assertFalse(activityList.hasActivity(editedActivity));
    }

    @Test
    public void getActivityList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> activityList.getActivityList().remove(0));
    }

    /**
     * A stub ReadOnlyTripList whose trips list can violate interface constraints.
     */
    private static class ActivityListStub implements ReadOnlyActivityList {
        private final ObservableList<Activity> activities = FXCollections.observableArrayList();

        ActivityListStub(Collection<Activity> activities) {
            this.activities.setAll(activities);
        }

        @Override
        public ObservableList<Activity> getActivityList() {
            return activities;
        }
    }

}
