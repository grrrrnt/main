package seedu.volant.trip.model;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.Page.TRIP;
import static seedu.volant.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.trip.Trip;

/**
 * Represents the in-memory model of the trip data on the TRIP page.
 */
public class TripModelManager implements Model {

    private final Trip trip;
    private final UserPrefs userPrefs;
    private final Page page = TRIP;

    /**
     * Initializes a TripModelManager with the given trip and userPrefs.
     */
    public TripModelManager(Trip trip, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(trip, userPrefs);

        LOGGER.fine("Initializing with trip: " + trip + " and user prefs " + userPrefs);

        this.trip = trip;
        this.userPrefs = new UserPrefs(userPrefs);
    }

    //=========== Trip ================================================================================


    @Override
    public Page getPage() {
        return page;
    }

    public Trip getTrip() {
        return trip;
    }

    public Itinerary getItinerary() {
        return trip.getItinerary();
    }

    public Journal getJournal() {
        return trip.getJournal();
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getVolantFilePath() {
        return userPrefs.getVolantFilePath();
    }

    @Override
    public void setVolantFilePath(Path volantFilePath) {
        requireNonNull(volantFilePath);
        userPrefs.setVolantFilePath(volantFilePath);
    }
}
