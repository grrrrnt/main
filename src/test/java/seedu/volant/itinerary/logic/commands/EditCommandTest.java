package seedu.volant.itinerary.logic.commands;

import static seedu.volant.itinerary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.testutil.TypicalActivities.getTypicalActivities;
import static seedu.volant.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.volant.testutil.TypicalTrips.getGermanyTrip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.volant.commons.model.UserPrefs;
import seedu.volant.itinerary.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.testutil.ActivityBuilder;
import seedu.volant.testutil.EditItineraryDescriptorBuilder;

public class EditCommandTest {
    public static final String MESSAGE_EDIT_ITINERARY_SUCCESS = "Edited activity: %1$s";
    private ItineraryModelManager model;

    @BeforeEach
    public void setUp() {
        model = new ItineraryModelManager(getGermanyTrip(), new UserPrefs());
        model.getTrip().getItinerary().getActivityList().setActivities(getTypicalActivities());
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Activity editedActivity = new ActivityBuilder().build();
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder(editedActivity).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ITINERARY_SUCCESS, editedActivity);

        ItineraryModelManager expectedModel = new ItineraryModelManager(model.getTrip(), new UserPrefs());

        expectedModel.setActivity(model.getFilteredActivityList().get(0), editedActivity);
        model = new ItineraryModelManager(getGermanyTrip(), new UserPrefs());
        model.getTrip().getItinerary().getActivityList().setActivities(getTypicalActivities());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
}
