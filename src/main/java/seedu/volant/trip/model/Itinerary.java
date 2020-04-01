package seedu.volant.trip.model;

import static seedu.volant.trip.model.Feature.ITINERARY;

import seedu.volant.itinerary.model.ActivityList;

/**
 * Represents an itinerary.
 */
public class Itinerary extends TripFeature {
    public static final Feature FEATURE = ITINERARY;

    // Ranice: I am not sure if the itinerary should be given a title or not.
    private String title;
    private ActivityList activityList;

    /**
     * Constructs itinerary with no title.
     */
    public Itinerary() {
        this.activityList = new ActivityList();
    }

    /**
     * Constructs itinerary with title {@param title}.
     */
    public Itinerary(String title) {
        this.title = title;
        // Initialising itinerary with empty activity list.
        this.activityList = new ActivityList();
    }

    /**
     * Constructs itinerary with the given {@param activityList}.
     */
    public Itinerary(ActivityList activityList) {
        this.activityList = activityList;
    }

    public ActivityList getActivityList() {
        return activityList;
    }

    public Feature getFeature() {
        return FEATURE;
    }

    @Override
    public int getNumItems() {
        return activityList.getUniqueActivityList().getSize();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityList // instanceof handles nulls
                && activityList.equals(((ActivityList) other).getActivityList()));
    }

    @Override
    public int hashCode() {
        return activityList.hashCode();
    }

}
