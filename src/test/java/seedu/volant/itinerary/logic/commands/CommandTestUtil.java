package seedu.volant.itinerary.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TITLE;
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
import seedu.volant.testutil.EditItineraryDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_TITLE_ZOO = "Go to the zoo";
    public static final String VALID_TITLE_SING = "Singing in the rain";

    public static final String VALID_LOCATION_ZOO = "Bali Indonesia";
    public static final String VALID_LOCATION_SING = "Jinsoul LOONA";

    public static final String VALID_DATE_ZOO = "2020-10-14";
    public static final String VALID_DATE_SING = "2021-06-02";

    public static final String FORMATTED_DATE_ZOO = "14-10-2020";
    public static final String FORMATTED_DATE_SING = "02-06-2021";

    public static final String VALID_TIME_ZOO = "12:00";
    public static final String VALID_TIME_SING = "02:00";

    public static final String TITLE_DESC_ZOO = " " + PREFIX_TITLE + VALID_TITLE_ZOO;
    public static final String TITLE_DESC_SING = " " + PREFIX_TITLE + VALID_TITLE_SING;

    public static final String LOCATION_DESC_ZOO = " " + PREFIX_LOCATION + VALID_LOCATION_ZOO;
    public static final String LOCATION_DESC_SING = " " + PREFIX_LOCATION + VALID_LOCATION_SING;

    public static final String TIME_DESC_ZOO = " " + PREFIX_TIME + VALID_TIME_ZOO;
    public static final String TIME_DESC_SING = " " + PREFIX_TIME + VALID_TIME_SING;

    public static final String DATE_DESC_ZOO = " " + PREFIX_DATE + FORMATTED_DATE_ZOO;
    public static final String DATE_DESC_SING = " " + PREFIX_DATE + FORMATTED_DATE_SING;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";

    public static final EditItineraryDescriptorBuilder DESC_ITINERARY_ZOO;
    public static final EditItineraryDescriptorBuilder DESC_ITINERARY_SING;

    static {
        DESC_ITINERARY_ZOO =
                new EditItineraryDescriptorBuilder().withTitle(VALID_TITLE_ZOO)
                        .withLocation(VALID_LOCATION_ZOO).withDate(VALID_DATE_ZOO)
                        .withTime(VALID_TIME_ZOO);

        DESC_ITINERARY_SING =
                new EditItineraryDescriptorBuilder().withTitle(VALID_TITLE_SING)
                        .withLocation(VALID_LOCATION_SING).withDate(VALID_DATE_SING)
                        .withTime(VALID_TIME_SING);
    }

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
