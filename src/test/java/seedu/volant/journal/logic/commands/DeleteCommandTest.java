package seedu.volant.journal.logic.commands;

import static seedu.volant.journal.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.testutil.TypicalEntries.getTypicalEntries;
import static seedu.volant.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.volant.testutil.TypicalTrips.getIndiaTrip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.volant.commons.model.UserPrefs;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.JournalModelManager;

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
}
