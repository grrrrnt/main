package seedu.volant.itinerary.logic.commands;

import static seedu.volant.itinerary.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.testutil.TypicalActivities.getTypicalActivities;
import static seedu.volant.testutil.TypicalTrips.getGermanyTrip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.volant.commons.model.UserPrefs;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.testutil.ActivityBuilder;

public class AddCommandIntegrationTest {

    private ItineraryModelManager model;

    @BeforeEach
    public void setUp() {
        model = new ItineraryModelManager(getGermanyTrip(), new UserPrefs());
        model.getTrip().getItinerary().getActivityList().setActivities(getTypicalActivities());
    }

    @Test
    public void execute_newActivity_success() {
        Activity validActivity = new ActivityBuilder().build();

        ItineraryModelManager expectedModel = new ItineraryModelManager(model.getTrip(), new UserPrefs());

        expectedModel.addActivity(validActivity);
        model = new ItineraryModelManager(getGermanyTrip(), new UserPrefs());
        model.getTrip().getItinerary().getActivityList().setActivities(getTypicalActivities());

        assertCommandSuccess(new AddCommand(validActivity), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validActivity), expectedModel);
    }

    @Test
    public void execute_duplicateActivity_throwsCommandException() {
        Activity activityInList = model.getActivityList().getActivityList().get(0);
        assertCommandFailure(new AddCommand(activityInList), model, AddCommand.MESSAGE_DUPLICATE_ITEM);
    }
}
