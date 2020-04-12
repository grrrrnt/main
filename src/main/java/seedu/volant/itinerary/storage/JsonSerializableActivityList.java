package seedu.volant.itinerary.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.itinerary.model.ActivityList;
import seedu.volant.itinerary.model.ReadOnlyActivityList;
import seedu.volant.itinerary.model.activity.Activity;


/**
 * An Immutable TripList that is serializable to JSON format.
 */
@JsonRootName(value = "volant")
public class JsonSerializableActivityList {

    public static final String MESSAGE_DUPLICATE_ACTIVITY = "Trip list contains duplicate activities.";

    private final List<JsonAdaptedActivity> activities = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableActivityList} with the given activities.
     */
    @JsonCreator
    public JsonSerializableActivityList(@JsonProperty("activities") List<JsonAdaptedActivity> activities) {
        this.activities.addAll(activities);
    }

    /**
     * Converts a given {@code ReadOnlyActivityList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableActivityList}.
     */
    public JsonSerializableActivityList(ReadOnlyActivityList source) {
        activities.addAll(source.getActivityList().stream().map(JsonAdaptedActivity::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TripList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ActivityList toModelType() throws IllegalValueException {
        ActivityList activityList = new ActivityList();
        for (JsonAdaptedActivity jsonAdaptedActivity : activities) {
            Activity activity = jsonAdaptedActivity.toModelType();
            if (activityList.hasActivity(activity)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ACTIVITY);
            }
            activityList.addActivity(activity);
        }
        return activityList;
    }

}

