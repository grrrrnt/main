package seedu.volant.itinerary.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.trip.model.Itinerary;

/**
 * Jackson-friendly version of {@link Itinerary}.
 */
public class JsonAdaptedItinerary {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Itinerary's %s field is missing!";

    private final String title;
    private final List<JsonAdaptedActivity> activityList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedItinerary} with the given title and activity list.
     */
    @JsonCreator
    public JsonAdaptedItinerary(@JsonProperty("title") String title,
                                @JsonProperty("activityList") List<JsonAdaptedActivity> activityList) {
        this.title = title;
        if (activityList != null) {
            this.activityList.addAll(activityList);
        }
    }

    /**
     * Converts a given {@code Itinerary} into this class for Jackson use.
     */
    public Itinerary toModelType() throws IllegalValueException {
        final List<Activity> activities = new ArrayList<>();

        for (JsonAdaptedActivity activity : activityList) {
            activities.add(activity.toModelType());
        }

        final String modelTitle = title;

        //final Set<Activity> modelActivities = new HashSet<>(activities);

        return new Itinerary(modelTitle);
    }

}
