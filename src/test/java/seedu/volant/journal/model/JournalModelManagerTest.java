package seedu.volant.journal.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.Assert.assertThrows;

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
import seedu.volant.testutil.TypicalEntries;



public class JournalModelManagerTest {
    private JournalModelManager modelManager = new JournalModelManager();

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
    public void hasEntry_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEntry(null));
    }

    @Test
    public void hasEntry_entryNotInEntryList_returnsFalse() {
        assertFalse(modelManager.hasEntry(TypicalEntries.C));
    }

    @Test
    public void hasEntry_entryInEntryList_returnsTrue() {
        modelManager.addEntry(TypicalEntries.C);
        assertTrue(modelManager.hasEntry(TypicalEntries.C));
    }

    @Test
    public void getFilteredEntryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEntryList().remove(0));
    }
}
