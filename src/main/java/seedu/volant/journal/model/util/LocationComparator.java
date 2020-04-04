package seedu.volant.journal.model.util;

import java.util.Comparator;

import seedu.volant.journal.model.Entry;

/**
 * Comparator for sorting by Location.
 */
public class LocationComparator implements Comparator<Entry> {
    @Override
    public int compare(Entry e1, Entry e2) {
        String l1 = e1.getLocationAsString();
        String l2 = e2.getLocationAsString();
        if (!l1.equals(l2)) {
            return l1.compareTo(l2);
        } else {
            return new NewestDateTimeComparator().compare(e1, e2);
        }
    }
}
