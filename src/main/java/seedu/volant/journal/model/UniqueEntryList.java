package seedu.volant.journal.model;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.volant.home.model.trip.Location;
import seedu.volant.journal.model.exceptions.EntryNotFoundException;
import seedu.volant.journal.model.util.FeelingComparator;
import seedu.volant.journal.model.util.LocationComparator;
import seedu.volant.journal.model.util.NewestDateTimeComparator;
import seedu.volant.journal.model.util.OldestDateTimeComparator;
import seedu.volant.journal.model.util.SortType;

/**
 * A list of entries that enforces uniqueness between its elements and does not allow nulls.
 * An entry is considered unique by comparing using {@code Entry#isSameEntry(Entry)}. As such, adding and updating of
 * entries uses Entry#isSameEntry(Entry) for equality so as to ensure that the entry being added or updated is
 * unique in terms of identity in the UniqueEntryList. However, the removal of an entry uses Entry#equals(Object) so
 * as to ensure that the entry with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueEntryList implements Iterable<Entry> {

    private final ObservableList<Entry> internalList = FXCollections.observableArrayList();
    private final ObservableList<Entry> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    /**
     * Returns true if the list contains an equivalent entry as the given argument.
     *
     */
    public boolean contains(Entry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEntry);
    }

    /**
     * Adds an entry to the list.
     * The entry must not already exist in the list.
     */
    public void add(Entry toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the entry {@code target} in the list with {@code editedEntry}.
     * {@code target} must exist in the list.
     * The entry identity of {@code editedEntry} must not be the same as another existing entry in the list.
     */
    public void setEntry(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }
        internalList.set(index, editedEntry);
    }

    /**
     * Removes the equivalent entry from the list.
     * The entry must exist in the list.
     */
    public void remove(Entry toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    public int getSize() {
        return internalList.size();
    }

    /**
     * Edits the equivalent entry from the list.
     * The entry must exist in the list.
     * Items in the {@code fields} are edited in the entry.
     */
    public void edit(Entry entry, HashMap<String, Object> fields) {
        requireNonNull(entry);
        for (Map.Entry<String, Object> e: fields.entrySet()) {
            switch (e.getKey()) {
            case "location":
                entry.setLocation((Location) e.getValue());
                break;
            case "text":
                entry.setText((String) e.getValue());
                break;
            case "weather":
                entry.setWeather((Weather) e.getValue());
                break;
            case "feeling":
                entry.setFeeling((Feeling) e.getValue());
                break;
            case "date":
                entry.setDate((LocalDate) e.getValue());
                break;
            case "time":
                entry.setTime((LocalTime) e.getValue());
                break;
            default:
            }
        }
    }

    /**
     * Replaces the contents of this list with {@code entries}.
     * {@code entries} must not contain duplicate entries.
     */

    public void setEntries(List<Entry> entries) {
        requireAllNonNull(entries);
        internalList.setAll(entries);
    }

    /**
     * Sorts the EntryList based on given SortType
     */
    public void sortEntries(SortType sortType) {
        requireNonNull(sortType);
        switch (sortType) {
        case NEW:
            FXCollections.sort(internalList, new NewestDateTimeComparator());
            break;
        case OLD:
            FXCollections.sort(internalList, new OldestDateTimeComparator());
            break;
        case LOCATION:
            FXCollections.sort(internalList, new LocationComparator());
            break;
        case FEELING:
            FXCollections.sort(internalList, new FeelingComparator());
            break;
        default:
            throw new InputMismatchException(SortType.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Entry> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Entry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEntryList // instanceof handles nulls
                && internalList.equals(((UniqueEntryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
