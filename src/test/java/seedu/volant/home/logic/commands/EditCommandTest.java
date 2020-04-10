package seedu.volant.home.logic.commands;

import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_DATEFROM_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_DATETO_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_LOCATION_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_TRIPNAME_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
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
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

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

    /*
    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Trip editedTrip = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedTrip);

        Model expectedModel = new HomeModelManager(new TripList(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Trip tripInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Trip editedTrip = new TripBuilder(tripInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditTripDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedTrip);

        Model expectedModel = new HomeModelManager(new TripList(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedTrip);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Trip firstTrip = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditTripDescriptorBuilder(firstTrip).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit trip in filtered list into a duplicate in address book
        Trip tripInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditTripDescriptorBuilder(tripInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditTripDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditTripDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

    */
}
