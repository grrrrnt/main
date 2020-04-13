package seedu.volant.testutil;

import seedu.volant.itinerary.model.ActivityList;
import seedu.volant.itinerary.model.activity.Activity;

/**
 * A utility class to help with building ActivityList objects.
 */
public class ActivityListBuilder {

    private ActivityList activityList;

    public ActivityListBuilder() {
        activityList = new ActivityList();
    }

    public ActivityListBuilder(ActivityList activityList) {
        this.activityList = activityList;
    }

    /**
     * Adds a new {@code Activitiy} to the {@code ActivityList} that we are building.
     */
    public ActivityListBuilder withActivity(Activity activity) {
        activityList.addActivity(activity);
        return this;
    }

    public ActivityList build() {
        return activityList;
    }
}
