package seedu.volant.journal.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_FEELING;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_WEATHER;
import static seedu.volant.itinerary.logic.commands.AddCommand.COMMAND_WORD;
import static seedu.volant.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.storage.JsonUserPrefsStorage;
import seedu.volant.commons.storage.JsonVolantStorage;
import seedu.volant.commons.storage.StorageManager;
import seedu.volant.journal.model.JournalModelManager;

public class JournalLogicManagerTest {
    @TempDir
    public Path temporaryFolder;

    private Model model = new JournalModelManager();
    private JournalLogicManager logic;

    @BeforeEach
    public void setUp() {
        JsonVolantStorage volantStorage =
                new JsonVolantStorage(temporaryFolder.resolve("volant.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(volantStorage, userPrefsStorage);
        logic = new JournalLogicManager((JournalModelManager) model, storage);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String addCommand = COMMAND_WORD + " "
                + PREFIX_DATE + "10-06-2022 "
                + PREFIX_TIME + "12:51 "
                + PREFIX_TEXT + "I am testing our app, Volant "
                + PREFIX_LOCATION + "At home cause of COVID-19 "
                + PREFIX_FEELING + "TIRED "
                + PREFIX_WEATHER + "HOT";

        String expectedMessage = "New journal entry added: \n"
                + "Date: 10 Jun 2022\n"
                + "Time: 12:51 PM\n"
                + "Text: I am testing our app, Volant\n"
                + "Location: At home cause of COVID-19\n"
                + "Feeling: TIRED\n"
                + "Weather: HOT";

        assertCommandSuccess(addCommand, expectedMessage, model);
    }

    @Test
    public void getFilteredEntryList_modifyEntry_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getModel().getFilteredEntryList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }
}
