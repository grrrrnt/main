package seedu.volant.itinerary.logic.commands;

import static seedu.volant.itinerary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.testutil.TypicalActivities.getTypicalActivities;
import static seedu.volant.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.volant.testutil.TypicalTrips.getGermanyTrip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.volant.commons.model.UserPrefs;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;

public class DeleteCommandTest {
    private ItineraryModelManager model;

    @BeforeEach
    public void setUp() {
        model = new ItineraryModelManager(getGermanyTrip(), new UserPrefs());
        model.getTrip().getItinerary().getActivityList().setActivities(getTypicalActivities());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Activity activityToDelete = model.getFilteredActivityList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete);

        ItineraryModelManager expectedModel = new ItineraryModelManager(model.getTrip(), new UserPrefs());

        expectedModel.deleteActivity(activityToDelete);

        model = new ItineraryModelManager(getGermanyTrip(), new UserPrefs());
        model.getTrip().getItinerary().getActivityList().setActivities(getTypicalActivities());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }
}
