package seedu.volant.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalTrips.A;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.model.UserPrefs;

//TODO: Refine test cases

public class ModelManagerTest {

    private HomeModelManager modelManager = new HomeModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TripList(), new TripList(modelManager.getTripList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setVolantFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setVolantFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setVolantFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setVolantFilePath(path);
        assertEquals(path, modelManager.getVolantFilePath());
    }

    @Test
    public void hasTrip_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTrip(null));
    }

    @Test
    public void hasTrip_tripNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasTrip(A));
    }

    @Test
    public void hasTrip_tripInAddressBook_returnsTrue() {
        modelManager.addTrip(A);
        assertTrue(modelManager.hasTrip(A));
    }

    @Test
    public void getFilteredTripList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTripList().remove(0));
    }

    /*
    @Test
    public void equals() {
        TripList addressBook = new TripListBuilder().withTrip(A).build();
        TripList differentAddressBook = new TripList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new HomeModelManager(addressBook, userPrefs);
        HomeModelManager modelManagerCopy = new HomeModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new HomeModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        modelManager.updateFilteredTripList(new NameContainsKeywordsPredicate(Arrays.asList(A.getName().toString())));
        assertFalse(modelManager.equals(new HomeModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTripList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setVolantFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new HomeModelManager(addressBook, differentUserPrefs)));
    }
    */
}
