package seedu.volant.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.volant.home.model.trip.Location;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.itinerary.model.activity.Title;

/**
 * A utility class to help with building Activity objects.
 */
public class ActivityBuilder {
    public static final String DEFAULT_TITLE = "Go to Brandenburg Cafe";
    public static final String DEFAULT_LOCATION = "Berlin, Germany";
    public static final String DEFAULT_DATE = "2022-06-10";
    public static final String DEFAULT_TIME = "12:00";

    private Title title;
    private LocalDate date;
    private LocalTime time;
    private Location location;

    public ActivityBuilder() {
        title = new Title(DEFAULT_TITLE);
        location = new Location(DEFAULT_LOCATION);
        date = LocalDate.parse(DEFAULT_DATE);
        time = LocalTime.parse(DEFAULT_TIME);
    }

    /**
     * Initializes the Itinerary with the data of {@code tripToCopy}.
     */
    public ActivityBuilder(Activity activityToCopy) {
        title = activityToCopy.getTitle();
        location = activityToCopy.getLocation();
        date = activityToCopy.getDate();
        time = activityToCopy.getTime();
    }

    /**
     * Sets the {@code Title} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withDate(String date) {
        this.date = LocalDate.parse(date);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withTime(String time) {
        this.time = LocalTime.parse(time);
        return this;
    }

    /**
     * Build the Activity object
     * @return the activity object that has been built
     */
    public Activity build() {
        Activity activity = new Activity(title, date, time, location);
        return activity;
    }
}
