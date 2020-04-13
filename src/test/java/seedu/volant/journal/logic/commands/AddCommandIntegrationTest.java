package seedu.volant.journal.logic.commands;

import static seedu.volant.journal.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.volant.commons.model.UserPrefs;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.testutil.EntryBuilder;
import seedu.volant.testutil.TypicalEntries;
import seedu.volant.testutil.TypicalTrips;

public class AddCommandIntegrationTest {
    private JournalModelManager model;

    @BeforeEach
    public void setUp() {
        model = new JournalModelManager(TypicalTrips.getIndiaTrip(), new UserPrefs());
        model.getTrip().getJournal().getEntryList().setEntries(TypicalEntries.getTypicalEntries());
    }

    @Test
    public void execute_newEntry_success() {
        Entry validEntry = new EntryBuilder().build();
        JournalModelManager expectedModel = new JournalModelManager(model.getTrip(), new UserPrefs());
        expectedModel.addEntry(validEntry);
        model = new JournalModelManager(TypicalTrips.getIndiaTrip(), new UserPrefs());
        model.getTrip().getJournal().getEntryList().setEntries(TypicalEntries.getTypicalEntries());

        assertCommandSuccess(new AddCommand(validEntry), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEntry), expectedModel);
    }
}
