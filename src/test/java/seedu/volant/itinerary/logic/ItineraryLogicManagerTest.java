package seedu.volant.itinerary.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TITLE;
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
import seedu.volant.itinerary.model.ItineraryModelManager;

public class ItineraryLogicManagerTest {
    @TempDir
    public Path temporaryFolder;

    private Model model = new ItineraryModelManager();
    private ItineraryLogicManager logic;

    @BeforeEach
    public void setUp() {
        JsonVolantStorage volantStorage =
                new JsonVolantStorage(temporaryFolder.resolve("volant.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(volantStorage, userPrefsStorage);
        logic = new ItineraryLogicManager((ItineraryModelManager) model, storage);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String addCommand = COMMAND_WORD + " "
                + PREFIX_TITLE + "Fly like a butterfly "
                + PREFIX_DATE + "10-06-2022 "
                + PREFIX_TIME + "12:00 "
                + PREFIX_LOCATION + "LOONA, BlockBerry Creative";

        String expectedMessage = "New activity added: \n"
                + "Title: Fly like a butterfly\n"
                + "Location: LOONA, BlockBerry Creative\n"
                + "Date: 10 Jun 2022\n"
                + "Time: 12:00 PM";

        assertCommandSuccess(addCommand, expectedMessage, model);
    }

    @Test
    public void getFilteredActivityList_modifyActivity_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getModel().getFilteredActivityList().remove(0));
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
