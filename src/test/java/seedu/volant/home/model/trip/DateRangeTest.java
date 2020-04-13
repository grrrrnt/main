package seedu.volant.home.model.trip;

import static seedu.volant.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateRangeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateRange(null, null));
    }

}
