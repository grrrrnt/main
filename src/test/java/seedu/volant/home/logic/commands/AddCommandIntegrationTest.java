package seedu.volant.home.logic.commands;

import static seedu.volant.home.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.volant.home.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.testutil.TypicalTrips.getTypicalTripList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.testutil.TripBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private HomeModelManager model;

    @BeforeEach
    public void setUp() {
        model = new HomeModelManager(getTypicalTripList(), new UserPrefs());
    }

    @Test
    public void execute_newTrip_success() {
        Trip validTrip = new TripBuilder().build();

        HomeModelManager expectedModel = new HomeModelManager(model
                .getTripList(), new UserPrefs());

        expectedModel.addTrip(validTrip);

        assertCommandSuccess(new AddCommand(validTrip), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTrip), expectedModel);
    }

    @Test
    public void execute_duplicateTrip_throwsCommandException() {
        Trip tripInList = model.getTripList().getTripList().get(0);
        assertCommandFailure(new AddCommand(tripInList), model, AddCommand.MESSAGE_DUPLICATE_TRIP);
    }

}
