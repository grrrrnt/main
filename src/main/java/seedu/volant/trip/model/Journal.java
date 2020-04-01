package seedu.volant.trip.model;

import static seedu.volant.trip.model.Feature.JOURNAL;

import seedu.volant.journal.model.EntryList;

/**
 * Represents a journal
 */
public class Journal extends TripFeature {
    public static final Feature FEATURE = JOURNAL;

    private String title;
    private EntryList entryList;

    /**
     * Constructs journal with no title.
     */
    public Journal() {
        this.entryList = new EntryList();
    }

    /**
     * Constructs journal with title {@param title}.
     */
    public Journal(String title) {
        this.title = title;
    }

    /**
     * Constructs journal with the given {@param entryList}.
     */
    public Journal(EntryList entryList) {
        this.entryList = entryList;
    }

    public EntryList getEntryList() {
        return entryList;
    }

    public Feature getFeature() {
        return FEATURE;
    }

    @Override
    public int getNumItems() {
        return entryList.getEntryList().size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EntryList // instanceof handles nulls
                && entryList.equals(((EntryList) other).getEntryList()));
    }

    @Override
    public int hashCode() {
        return entryList.hashCode();
    }
}
