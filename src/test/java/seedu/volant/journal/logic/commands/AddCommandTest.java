package seedu.volant.journal.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.EntryList;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.journal.model.util.SortType;
import seedu.volant.testutil.EntryBuilder;
import seedu.volant.trip.model.Journal;

public class AddCommandTest {
    @Test
    public void constructor_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void equals() {
        Entry austria = new EntryBuilder().withLocation("Austria").build();
        Entry burma = new EntryBuilder().withLocation("Burma").build();

        AddCommand austriaCommand = new AddCommand(austria);
        AddCommand burmaCommand = new AddCommand(burma);

        // same object -> returns true
        assertTrue(austriaCommand.equals(austriaCommand));

        // same values -> returns true
        AddCommand austriaCommandCopy = new AddCommand(austria);
        assertTrue(austriaCommand.equals(austriaCommandCopy));

        // different types -> returns false
        assertFalse(austriaCommand.equals(1));

        // null -> returns false
        assertFalse(austriaCommand.equals(null));

        // different trip -> returns false
        assertFalse(austriaCommand.equals(burmaCommand));
    }

    private class ModelStub extends JournalModelManager {
        @Override
        public Page getPage() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Trip getTrip() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Journal getJournal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public EntryList getEntryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntry(Entry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEntry(Entry target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEntry(Entry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editEntry(Entry entry, Entry editedEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortEntries(SortType sortType) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Entry> getPredicateShowAllEntries() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Entry> getFilteredEntryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEntryList(Predicate<Entry> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean equals(Object obj) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getVolantFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setVolantFilePath(Path volantFilePath) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithSingleEntry extends ModelStub {
        private final Trip trip;

        public ModelStubWithSingleEntry(Trip trip) {
            requireNonNull(trip);
            this.trip = trip;
        }

        @Override
        public boolean hasEntry(Entry entry) {
            return super.hasEntry(entry);
        }


    }
}
