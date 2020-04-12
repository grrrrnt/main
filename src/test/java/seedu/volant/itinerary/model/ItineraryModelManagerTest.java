package seedu.volant.itinerary.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalActivities.C;
import static seedu.volant.testutil.TypicalTrips.A;
import static seedu.volant.testutil.TypicalTrips.B;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.trip.DateRange;
import seedu.volant.home.model.trip.Location;
import seedu.volant.home.model.trip.Name;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.testutil.TripBuilder;

public class ItineraryModelManagerTest {

    private ItineraryModelManager modelManager = new ItineraryModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Trip(new Name("Berlin berlin"), new Location("Berlin, Germany"),
                new DateRange(LocalDate.parse("2022-06-10"), LocalDate.parse("2022-06-21"))),
                        new Trip(modelManager.getTrip()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setVolantFilePath(Paths.get("volant/trip/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setVolantFilePath(Paths.get("new/volant/trip/file/path"));
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
    public void setVolantFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setVolantFilePath(null));
    }

    @Test
    public void setVolantFilePath_validPath_setsVolantFilePath() {
        Path path = Paths.get("volant/trip/file/path");
        modelManager.setVolantFilePath(path);
        assertEquals(path, modelManager.getVolantFilePath());
    }

    @Test
    public void hasActivity_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasActivity(null));
    }

    @Test
    public void hasActivity_activityNotInActivityList_returnsFalse() {
        assertFalse(modelManager.hasActivity(C));
    }

    @Test
    public void hasActivity_activityInActivityList_returnsTrue() {
        modelManager.addActivity(C);
        assertTrue(modelManager.hasActivity(C));
    }

    @Test
    public void getFilteredActivityList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredActivityList().remove(0));
    }

    @Test
    public void equals() {
        Trip trip = new TripBuilder(A).build();
        Trip differentTrip = new TripBuilder(B).build();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ItineraryModelManager(trip, userPrefs);
        ItineraryModelManager modelManagerCopy = new ItineraryModelManager(trip, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different Trip -> returns false
        assertFalse(modelManager.equals(new ItineraryModelManager(differentTrip, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredActivityList(modelManager.getPredicateShowAllActivities());

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setVolantFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ItineraryModelManager(trip, differentUserPrefs)));
    }

}
