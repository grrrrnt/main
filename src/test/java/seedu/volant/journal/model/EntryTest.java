package seedu.volant.journal.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.TypicalEntries.B;
import static seedu.volant.testutil.TypicalEntries.C;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.volant.home.model.trip.Location;
import seedu.volant.testutil.EntryBuilder;



public class EntryTest {
    @Test
    public void isSameEntryTest() {
        //same entry -> true
        assertTrue(B.isSameEntry(B));

        //null entry -> false
        assertFalse(B.isSameEntry(null));

        //different entries -> false
        assertFalse(B.isSameEntry(C));

        //all fields same but different date -> false
        Entry editedB = new EntryBuilder(B).withDate("1996-01-31").build();
        assertFalse(B.isSameEntry(editedB));
    }

    @Test
    public void getLocationTest() {
        //compare to Location variable -> true
        assertTrue(B.getLocation().equals(new Location("Purani Dili")));

        //compare to location as string -> false
        assertFalse(B.getLocation().equals("Purani Dili"));
    }

    @Test
    public void getFeelingTest() {
        //compare to Feeling variable -> true
        assertTrue(B.getFeeling().equals(Feeling.valueOf("TIRED")));

        //compare to feeling as string -> false
        assertFalse(B.getFeeling().equals("TIRED"));
    }

    @Test
    public void getDateTest() {
        //return LocalDate variable -> true
        assertTrue(B.getDate() instanceof LocalDate);

        //return string variable -> false
        assertFalse(B.getDate().equals("2018-12-13"));

        assertEquals(B.getDate(), LocalDate.parse("2018-12-13"));
    }

    @Test
    public void getTimeTest() {
        //return LocalTime variable -> true
        assertTrue(B.getTime() instanceof LocalTime);

        //return string variable -> false
        assertFalse(B.getTime().equals("23:59"));

        assertEquals(B.getTime(), LocalTime.parse("23:59"));
    }

    @Test
    public void getWeatherTest() {
        //compare to Weather variable -> true
        assertTrue(B.getWeather().equals(Weather.valueOf("COLD")));

        //compare to feeling as string -> false
        assertFalse(B.getWeather().equals("COLD"));
    }

    @Test
    public void getTextTest() {
        assertEquals(B.getText(), "I'm having chai tea at some random stall along the highway");
    }
}
