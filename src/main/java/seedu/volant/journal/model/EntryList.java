package seedu.volant.journal.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.volant.journal.model.util.SortType;

/**
 * Wraps all data at the Journal level
 * Duplicates are not allowed (by .isSameEntry comparison)
 */
public class EntryList implements ReadOnlyEntryList {

    private final UniqueEntryList entries;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        entries = new UniqueEntryList();
    }

    public EntryList() {

    }


    /**
     * Creates an EntryList using the entries in the {@code toBeCopied}
     */
    public EntryList(ReadOnlyEntryList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the entry list with {@code entries}.
     * {@code entries} must not contain duplicate entries.
     */

    public void setEntries(List<Entry> entries) {
        this.entries.setEntries(entries);
    }

    /**
     * Resets the existing data of this {@code EntryList} with {@code newData}.
     */

    public void resetData(ReadOnlyEntryList newData) {
        requireNonNull(newData);

        setEntries(newData.getEntryList());
    }

    /**
     * Returns true if a entry with the same identity as {@code entry} exists in the entry list.
     */
    public boolean hasEntry(Entry entry) {
        requireNonNull(entry);
        return entries.contains(entry);
    }


    /**
     * Adds a entry to the journal.
    */
    public void addEntry(Entry p) {
        entries.add(p);
    }

    /**
     * Edits a entry to the journal.
     */
    public void setEntry(Entry p, Entry editedEntry) {
        entries.setEntry(p, editedEntry);
    }

    /**
     * Removes {@code key} from this {@code EntryList}.
     * {@code key} must exist in the address book.
    */
    public void removeEntry(Entry key) {
        entries.remove(key);
    }

    public void sortEntries(SortType sortType) {
        entries.sortEntries(sortType);
    }


    //// util methods


    @Override
    public String toString() {
        return entries.asUnmodifiableObservableList().size() + " asscheeks";
    }


    public UniqueEntryList getUniqueEntryList() {
        return entries;
    }

    @Override
    public ObservableList<Entry> getEntryList() {
        return entries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EntryList // instanceof handles nulls
                && entries.equals(((EntryList) other).entries));
    }

    @Override
    public int hashCode() {
        return entries.hashCode();
    }

}
