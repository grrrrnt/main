package seedu.volant.journal.logic.commands;

import static seedu.volant.testutil.TypicalEntries.getTypicalEntries;
import static seedu.volant.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.volant.testutil.TypicalTrips.getIndiaTrip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.volant.commons.model.UserPrefs;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.testutil.EditEntryDescriptorBuilder;
import seedu.volant.testutil.EntryBuilder;


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
}
