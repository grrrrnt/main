package seedu.volant.journal.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.volant.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.EntryList;
import seedu.volant.journal.model.JournalModelManager;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, JournalModelManager actualModel,
                                            CommandResult expectedCommandResult,
                                            JournalModelManager expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess
     * (Command, JournalModelManager, CommandResult, JournalModelManager)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, JournalModelManager actualModel, String expectedMessage,
                                            JournalModelManager expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered trip list and selected trip in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, JournalModelManager actualModel,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        EntryList expectedList = new EntryList(actualModel.getEntryList());
        List<Entry> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEntryList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedList, actualModel.getEntryList());
        assertEquals(expectedFilteredList, actualModel.getFilteredEntryList());
    }
}
