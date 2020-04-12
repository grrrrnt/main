package seedu.volant.itinerary.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.volant.itinerary.storage.JsonAdaptedActivity.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalActivities.B;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.core.Messages;
import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.home.model.trip.Location;
import seedu.volant.itinerary.model.activity.Title;

class JsonAdaptedActivityTest {

    private static final String INVALID_TITLE = "R@chel";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_DATE = "2020-32-01";
    private static final String INVALID_TIME = "24:59";

    private static final String VALID_TITLE = B.getTitle().toString();
    private static final String VALID_LOCATION = B.getLocation().toString();
    private static final String VALID_DATE = "01 May 2020";
    private static final String VALID_TIME = B.getTime().toString();


    @Test
    public void toModelType_validActivityDetails_returnsActivity() throws Exception {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(B);
        assertEquals(B, activity.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(INVALID_TITLE, VALID_LOCATION, VALID_DATE, VALID_TIME);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(null, VALID_LOCATION, VALID_DATE, VALID_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(VALID_TITLE, INVALID_LOCATION, VALID_DATE, VALID_TIME);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(VALID_TITLE, null, VALID_DATE, VALID_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(VALID_TITLE, VALID_LOCATION, INVALID_DATE, VALID_TIME);
        String expectedMessage = Messages.MESSAGE_ERROR_LOADING_DATE;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(VALID_TITLE, VALID_LOCATION, VALID_DATE, INVALID_TIME);
        String expectedMessage = Messages.MESSAGE_ERROR_LOADING_TIME;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

}
