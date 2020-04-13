package seedu.volant.journal.logic.commands;

import static seedu.volant.journal.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.testutil.TypicalEntries.getTypicalEntries;
import static seedu.volant.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.volant.testutil.TypicalTrips.getIndiaTrip;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.EntryList;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.journal.model.util.SortType;
import seedu.volant.trip.model.Journal;



public class DeleteCommandTest {
    private JournalModelManager model;

    @BeforeEach
    public void setUp() {
        model = new JournalModelManager(getIndiaTrip(), new UserPrefs());
        model.getTrip().getJournal().getEntryList().setEntries(getTypicalEntries());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Entry entryToDelete = model.getFilteredEntryList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS, entryToDelete);

        JournalModelManager expectedModel = new JournalModelManager(model.getTrip(), new UserPrefs());

        expectedModel.deleteEntry(entryToDelete);

        model = new JournalModelManager(getIndiaTrip(), new UserPrefs());
        model.getTrip().getJournal().getEntryList().setEntries(getTypicalEntries());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
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
}
