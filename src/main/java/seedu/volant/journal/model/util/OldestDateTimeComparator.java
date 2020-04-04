package seedu.volant.journal.model.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

import seedu.volant.journal.model.Entry;

/**
 * Comparator for sorting by oldest entry first
 */
public class OldestDateTimeComparator implements Comparator<Entry> {
    @Override
    public int compare(Entry e1, Entry e2) {
        LocalDate date1 = e1.getDate();
        LocalDate date2 = e2.getDate();
        LocalTime time1 = e1.getTime();
        LocalTime time2 = e2.getTime();
        if (!date1.isEqual(date2)) {
            return date1.compareTo(date2);
        } else {
            return time1.compareTo(time2);
        }
    }
}
