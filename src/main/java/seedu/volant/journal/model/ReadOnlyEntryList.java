package seedu.volant.journal.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of the activity list.
 */
public interface ReadOnlyEntryList {

    /**
     * Returns an unmodifiable view of the entire itinerary.
     */
    ObservableList<Entry> getEntryList();
}
