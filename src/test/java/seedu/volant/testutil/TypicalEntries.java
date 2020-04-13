package seedu.volant.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.EntryList;

/**
 * A utility class containing a list of {@code Entry} objects to be used in tests.
 */
public class TypicalEntries {
    public static final Entry A = new EntryBuilder().withDate("2018-12-12")
            .withTime("09:11")
            .withText("I can't believe I'm India :O")
            .withFeeling("EXCITED")
            .withLocation("Delhi Airport")
            .withWeather("SUNNY").build();

    public static final Entry B = new EntryBuilder().withDate("2018-12-13")
            .withTime("23:59")
            .withText("I'm having chai tea at some random stall along the highway")
            .withFeeling("TIRED")
            .withLocation("Purani Dili")
            .withWeather("COLD").build();

    public static final Entry C = new EntryBuilder().withDate("2018-12-16")
            .withTime("07:09")
            .withText("Spoke to mummy just now, she's feeling better")
            .withFeeling("WORRIED")
            .withLocation("Kashmir")
            .withWeather("SNOWY").build();

    public static final Entry D = new EntryBuilder().withDate("2018-12-19")
            .withTime("16:09")
            .withText("I'm in love with this place, hate that I must leave tomorrow")
            .withFeeling("HAPPY")
            .withLocation("Manali")
            .withWeather("COOL").build();

    private TypicalEntries() {}

    public static EntryList getTypicalEntryList() {
        EntryList list = new EntryList();
        list.addEntry(A);
        list.addEntry(B);
        list.addEntry(C);
        list.addEntry(D);
        return list;
    }

    public static List<Entry> getTypicalEntries() {
        return new ArrayList<>(Arrays.asList(A, B, C, D));
    }
}
