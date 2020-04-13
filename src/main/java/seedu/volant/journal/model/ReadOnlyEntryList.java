package seedu.volant.journal.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of the entry list.
 */
public interface ReadOnlyEntryList {

    /**
     * Returns an unmodifiable view of the entire journal.
     */
    ObservableList<Entry> getEntryList();
}
