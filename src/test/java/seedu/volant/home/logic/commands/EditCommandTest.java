package seedu.volant.home.logic.commands;

import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_DATEFROM_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_DATETO_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_LOCATION_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_TRIPNAME_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.volant.testutil.TypicalTrips.getTypicalTripList;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.testutil.EditTripDescriptorBuilder;
import seedu.volant.testutil.TripBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private HomeModelManager model = new HomeModelManager(getTypicalTripList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Trip editedTrip = new TripBuilder().build();
        EditCommand.EditTripDescriptor descriptor = new EditTripDescriptorBuilder(editedTrip).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TRIP_SUCCESS, editedTrip);

        HomeModelManager expectedModel =
                new HomeModelManager(new TripList(model.getTripList()), new UserPrefs());
        expectedModel.setTrip(model.getFilteredTripList().get(0), editedTrip);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredTripList().size());
        Trip lastTrip = model.getFilteredTripList().get(indexLastPerson.getZeroBased());

        TripBuilder personInList = new TripBuilder(lastTrip);
        Trip editedTrip = personInList.withName(VALID_TRIPNAME_CNY).withLocation(VALID_LOCATION_CNY)
                .withDateRange(VALID_DATEFROM_CNY, VALID_DATETO_CNY).build();

        EditCommand.EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withName(VALID_TRIPNAME_CNY)
                .withLocation(VALID_LOCATION_CNY).withDateRange(VALID_DATEFROM_CNY, VALID_DATETO_CNY)
                .build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TRIP_SUCCESS, editedTrip);

        HomeModelManager expectedModel =
                new HomeModelManager(new TripList(model.getTripList()), new UserPrefs());
        expectedModel.setTrip(lastTrip, editedTrip);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

}
