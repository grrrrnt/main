package seedu.volant.itinerary.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalTrips.getGermanyTrip;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.itinerary.model.ActivityList;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.testutil.ActivityBuilder;
import seedu.volant.testutil.ActivityListBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_tripAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingActivityAdded modelStub = new ModelStubAcceptingActivityAdded(getGermanyTrip());
        Activity validActivity = new ActivityBuilder().build();

        CommandResult commandResult = new AddCommand(validActivity).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validActivity), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validActivity), modelStub.activitiesAdded);
    }

    @Test
    public void execute_duplicateTrip_throwsCommandException() {
        Trip germanyTrip = getGermanyTrip();
        Activity validActivity = new ActivityBuilder().build();
        AddCommand addCommand = new AddCommand(validActivity);
        ModelStub modelStub = new ModelStubWithActivity(germanyTrip);
        modelStub.setActivityList(new ActivityListBuilder().withActivity(validActivity).build());

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_ITEM, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Activity alice = new ActivityBuilder().withTitle("Alice").build();
        Activity bob = new ActivityBuilder().withTitle("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different trip -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub extends ItineraryModelManager {
        @Override
        public Page getPage() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getVolantFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setVolantFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addActivity(Activity activity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setActivityList(ActivityList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ActivityList getActivityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasActivity(Activity activity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteActivity(Activity activity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setActivity(Activity target, Activity editedActivity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Activity> getFilteredActivityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredActivityList(Predicate<Activity> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single trip with a single activity.
     */
    private class ModelStubWithActivity extends ModelStub {
        private final Trip trip;

        ModelStubWithActivity(Trip trip) {
            requireNonNull(trip);
            this.trip = trip;
        }

        @Override
        public boolean hasActivity(Activity activity) {
            requireNonNull(activity);
            return this.trip.getItinerary().getActivityList().getActivityList().get(0).equals(activity);
        }

        @Override
        public void setActivityList(ActivityList activityList) {
            trip.getItinerary().getActivityList().setActivities(activityList.getActivityList());
        }

    }

    /**
     * A Model stub that always accept the activity being added into the current trip.
     */
    private class ModelStubAcceptingActivityAdded extends ModelStub {
        final ArrayList<Activity> activitiesAdded = new ArrayList<>();
        final Trip trip;

        ModelStubAcceptingActivityAdded(Trip trip) {
            requireNonNull(trip);
            this.trip = trip;
        }

        @Override
        public boolean hasActivity(Activity activity) {
            requireNonNull(activity);
            return activitiesAdded.stream().anyMatch(activity::equals);
        }

        @Override
        public void addActivity(Activity activity) {
            requireNonNull(activity);
            activitiesAdded.add(activity);
        }

        @Override
        public ActivityList getActivityList() {
            return new ActivityList();
        }
    }
}
