package seedu.volant.testutil;

import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.EntryList;

/**
 * A utility class to help with building EntryList objects.
 */
public class EntryListBuilder {
    private EntryList entryList;

    public EntryListBuilder() {
        entryList = new EntryList();
    }

    /**
     * Adds a new {@code Entry} to the {@code EntryList} that we are building.
     */
    public EntryListBuilder withEntry(Entry entry) {
        entryList.addEntry(entry);
        return this;
    }

    public EntryList build() {
        return entryList;
    }
}
