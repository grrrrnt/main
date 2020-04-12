package seedu.volant.itinerary.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.itinerary.model.ActivityList;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.itinerary.model.activity.TitleContainsKeywordsPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, ItineraryModelManager actualModel,
                                            CommandResult expectedCommandResult,
                                            ItineraryModelManager expectedModel) {
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
     * (Command, ItineraryModelManager, CommandResult, ItineraryModelManager)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, ItineraryModelManager actualModel, String expectedMessage,
                                            ItineraryModelManager expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered trip list and selected trip in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, ItineraryModelManager actualModel,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ActivityList expectedList = new ActivityList(actualModel.getActivityList());
        List<Activity> expectedFilteredList = new ArrayList<>(actualModel.getFilteredActivityList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedList, actualModel.getActivityList());
        assertEquals(expectedFilteredList, actualModel.getFilteredActivityList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the trip at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showActivityAtIndex(ItineraryModelManager model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredActivityList().size());

        Activity activity = model.getFilteredActivityList().get(targetIndex.getZeroBased());
        model.updateFilteredActivityList(
                new TitleContainsKeywordsPredicate(Arrays.asList(activity.getTitle().toString())));

        assertEquals(1, model.getFilteredActivityList().size());
    }
}
