package seedu.volant.commons.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.volant.home.storage.JsonAdaptedTrip.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalTrips.B;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.home.model.trip.DateRange;
import seedu.volant.home.model.trip.Location;
import seedu.volant.home.model.trip.Name;
import seedu.volant.home.storage.JsonAdaptedTrip;

public class JsonAdaptedTripTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_DATERANGE = "2020-43-93 to 2020-12-01";

    private static final String VALID_NAME = B.getName().toString();
    private static final String VALID_LOCATION = B.getLocation().toString();
    private static final String VALID_DATERANGE = B.getDateRange().toString();

    @Test
    public void toModelType_validTripDetails_returnsTrip() throws Exception {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(B);
        assertEquals(B, trip.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(INVALID_NAME, VALID_LOCATION, VALID_DATERANGE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(null, VALID_LOCATION, VALID_DATERANGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(VALID_NAME, INVALID_LOCATION, VALID_DATERANGE);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_NAME, null, VALID_DATERANGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_invalidDateRange_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(VALID_NAME, VALID_LOCATION, INVALID_DATERANGE);
        String expectedMessage = DateRange.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullDateRange_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_NAME, VALID_LOCATION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateRange.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

}
