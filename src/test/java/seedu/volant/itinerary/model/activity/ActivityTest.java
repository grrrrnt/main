package seedu.volant.itinerary.model.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.TypicalActivities.A;
import static seedu.volant.testutil.TypicalActivities.B;

import org.junit.jupiter.api.Test;

import seedu.volant.testutil.ActivityBuilder;

public class ActivityTest {

    @Test
    public void isSameActivity() {
        // same object -> returns true
        assertTrue(A.isSameActivity(A));

        // null -> returns false
        assertFalse(A.equals(null));

        // different date and time -> returns false
        Activity editedA = new ActivityBuilder(A).withDate(B.getDate().toString())
                .withTime(B.getTime().toString()).build();
        assertFalse(A.isSameActivity(editedA));

        // different title -> returns false
        editedA = new ActivityBuilder(A).withTitle(B.getTitle().toString()).build();
        assertFalse(A.isSameActivity(editedA));

        // same name, same date, different attributes -> returns false
        editedA = new ActivityBuilder(A).withLocation(B.getLocation().toString())
                .withTime(B.getTime().toString()).build();
        assertFalse(A.isSameActivity(editedA));

        // same name, same time, different attributes -> returns false
        editedA = new ActivityBuilder(A).withLocation(B.getLocation().toString())
                .withDate(B.getDate().toString()).build();
        assertFalse(A.isSameActivity(editedA));

        // same name, same location, same date, different attributes -> returns false
        editedA = new ActivityBuilder(A).withTime(B.getTime().toString()).build();
        assertFalse(A.isSameActivity(editedA));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Activity aCopy = new ActivityBuilder(A).build();
        assertTrue(A.equals(aCopy));

        // same object -> returns true
        assertEquals(A, A);

        // null -> returns false
        assertNotEquals(null, A);

        // different type -> returns false
        assertNotEquals(5, A);

        // different Activity -> returns false
        assertNotEquals(A, B);

        // different name -> returns false
        Activity editedA = new ActivityBuilder(A).withTitle(B.getTitle().toString()).build();
        assertFalse(A.isSameActivity(editedA));

        // different location -> returns false
        editedA = new ActivityBuilder(A).withLocation("Beijing").build();
        assertFalse(A.isSameActivity(editedA));

        // different date -> returns false
        editedA = new ActivityBuilder(A).withDate(B.getDate().toString()).build();
        assertFalse(A.isSameActivity(editedA));

        // different time -> returns false
        editedA = new ActivityBuilder(A).withTime(B.getTime().toString()).build();
        assertFalse(A.isSameActivity(editedA));
    }

}
