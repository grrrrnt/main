package seedu.volant.commons.model;

import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.logic.Page;
import seedu.volant.home.model.TripList;

/**
 * The API of the Model component.
 */
public interface Model {

    Logger LOGGER = LogsCenter.getLogger(Model.class);

    TripList getTripList();

    Page getPage();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getVolantFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setVolantFilePath(Path addressBookFilePath);


}
