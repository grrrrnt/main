package seedu.volant.itinerary.storage;

import static seedu.volant.commons.util.StringUtil.formatDate;
import static seedu.volant.commons.util.StringUtil.formatTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.home.model.trip.Location;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.itinerary.model.activity.Title;

/**
 * Jackson-friendly version of {@link Activity}.
 */
public class JsonAdaptedActivity {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Trip's %s field is missing!";

    private final String title;
    private final String location;
    private final String date;
    private final String time;

    /**
     * Constructs a {@code JsonAdaptedTrip} with the given trip details.
     */
    @JsonCreator
    public JsonAdaptedActivity(@JsonProperty("title") String title, @JsonProperty("location") String location,
                           @JsonProperty("date") String date, @JsonProperty("time") String time) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    /**
     * Converts a given {@code Activity} into this class for Jackson use.
     */
    public JsonAdaptedActivity(Activity source) {
        title = source.getTitle().toString();
        location = source.getLocation().toString();
        date = formatDate(source.getDate());
        time = formatTime(source.getTime());
    }

    /**
     * Converts this Jackson-friendly adapted trip object into the model's {@code Activity} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted trip.
     */
    public Activity toModelType() throws IllegalValueException {

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }

        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }

        final Title modelTitle = new Title(title);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }

        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }

        final Location modelLocation = new Location(location);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDate.class.getSimpleName()));
        }
        DateTimeFormatter dateParser = DateTimeFormatter.ofPattern("dd MMM yyyy");
        final LocalDate modelDate = LocalDate.parse(date, dateParser);
        /*
        TODO resolve this if possible
        if (!LocalDate.isValidDate(date)) {
            throw new IllegalValueException(LocalDate.MESSAGE_CONSTRAINTS);
        }
         */

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalTime.class.getSimpleName()));
        }
        DateTimeFormatter timeParser = DateTimeFormatter.ofPattern("hh:mm a");
        final LocalTime modelTime = LocalTime.parse(time, timeParser);
        /*
        TODO resolve this if necessary
        if (!LocalTime.isValidTime(time)) {
            throw new IllegalValueException(LocalTime.MESSAGE_CONSTRAINTS);
        }
         */

        return new Activity(modelTitle, modelDate, modelTime, modelLocation);
    }

}
