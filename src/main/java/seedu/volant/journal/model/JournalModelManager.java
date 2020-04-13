package seedu.volant.journal.model;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.Page.JOURNAL;
import static seedu.volant.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.trip.DateRange;
import seedu.volant.home.model.trip.Location;
import seedu.volant.home.model.trip.Name;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.journal.model.util.SortType;
import seedu.volant.trip.model.Journal;

/**
 * Represents the in-memory model of the journal data.
 */
public class JournalModelManager implements Model {

    private final Predicate<Entry> predicateShowAllEntries = unused -> true;
    private final Trip trip;
    private final Journal journal;
    private EntryList entryList;
    private final UserPrefs userPrefs;
    private final Page page = JOURNAL;
    private final FilteredList<Entry> filteredEntries;

    /**
     * Initializes a JournalModelManager with the given tripList, trip, journal, and userPrefs.
     */
    public JournalModelManager(Trip trip, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(trip, userPrefs);

        LOGGER.fine("You are now in the JOURNAL page of TRIP: " + trip + ".");

        this.trip = trip;
        this.journal = trip.getTripFeatureList().getJournal();
        this.userPrefs = new UserPrefs(userPrefs);
        this.entryList = journal.getEntryList();
        this.filteredEntries = new FilteredList<>(this.entryList.getEntryList());
    }

    public JournalModelManager() {
        Name tripName = new Name("Berlin berlin");
        Location tripLocation = new Location("Berlin, Germany");
        DateRange tripDateRange = new DateRange(LocalDate.parse("2022-06-10"), LocalDate.parse("2022-06-21"));

        Trip placeholder = new Trip(tripName, tripLocation, tripDateRange);
        this.trip = placeholder;
        this.journal = trip.getTripFeatureList().getJournal();
        this.userPrefs = new UserPrefs();
        this.entryList = journal.getEntryList();
        this.filteredEntries = new FilteredList<>(this.entryList.getEntryList());
    }

    @Override
    public Page getPage() {
        return page;
    }

    public Trip getTrip() {
        return trip;
    }

    public Journal getJournal() {
        return journal;
    }

    public EntryList getEntryList() {
        return this.entryList;
    }

    /**
     * Returns true if entry is within the entry list within model.
     */
    public boolean hasEntry(Entry entry) {
        requireNonNull(entry);
        return entryList.hasEntry(entry);
    }

    /**
     * Removes specified target {@code Entry} from entry list within model.
     */
    public void deleteEntry(Entry target) {
        entryList.removeEntry(target);
        updateFilteredEntryList(predicateShowAllEntries);

    }

    /**
     * Adds entry to entry list within model.
     */
    public void addEntry(Entry entry) {
        this.entryList.addEntry(entry);
        updateFilteredEntryList(predicateShowAllEntries);
    }

    /**
     * Edits specified target {@code Entry} from entry list to the specified values.
     * If the field is set to NULL, it will be ignored.
     * Only specified fields are edited.
     */
    public void editEntry(Entry entry, Entry editedEntry) {
        this.entryList.setEntry(entry, editedEntry);
        updateFilteredEntryList(predicateShowAllEntries);
    }

    /**
     * Sorts the EntryList based on given SortType
     */
    public void sortEntries(SortType sortType) {
        entryList.sortEntries(sortType);
        updateFilteredEntryList(predicateShowAllEntries);
    }

    public Predicate<Entry> getPredicateShowAllEntries() {
        return predicateShowAllEntries;
    }

    //=========== Filtered Trip List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Entry} backed by the internal list of
     * {@code versionedAddressBook}
     */

    public ObservableList<Entry> getFilteredEntryList() {
        return filteredEntries;
    }

    /**
     * Updates the filtered entry list according to the given predicate.
     */
    public void updateFilteredEntryList(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        filteredEntries.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof JournalModelManager)) {
            return false;
        }

        // state check
        JournalModelManager other = (JournalModelManager) obj;
        return trip.equals(other.trip)
                && userPrefs.equals(other.userPrefs);
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getVolantFilePath() {
        return userPrefs.getVolantFilePath();
    }

    @Override
    public void setVolantFilePath(Path volantFilePath) {
        requireNonNull(volantFilePath);
        userPrefs.setVolantFilePath(volantFilePath);
    }

}
