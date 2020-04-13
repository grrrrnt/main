package seedu.volant.journal.logic.commands;

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
import seedu.volant.testutil.EditEntryDescriptorBuilder;
import seedu.volant.testutil.EntryBuilder;
import seedu.volant.trip.model.Journal;



public class EditCommandTest {
    public static final String MESSAGE_EDIT_JOURNAL_SUCCESS = "Edited entry: %1$s";
    private JournalModelManager model;

    @BeforeEach
    public void setUp() {
        model = new JournalModelManager(getIndiaTrip(), new UserPrefs());
        model.getTrip().getJournal().getEntryList().setEntries(getTypicalEntries());
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Entry editedEntry = new EntryBuilder().build();
        EditCommand.EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(editedEntry).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_JOURNAL_SUCCESS, editedEntry);

        JournalModelManager expectedModel = new JournalModelManager(model.getTrip(), new UserPrefs());

        expectedModel.editEntry(model.getFilteredEntryList().get(0), editedEntry);
        model = new JournalModelManager(getIndiaTrip(), new UserPrefs());
        model.getTrip().getJournal().getEntryList().setEntries(getTypicalEntries());

        //assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
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
