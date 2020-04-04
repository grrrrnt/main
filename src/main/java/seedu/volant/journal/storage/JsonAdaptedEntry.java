package seedu.volant.journal.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.home.model.trip.Location;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.Feeling;
import seedu.volant.journal.model.Weather;

/**
 * Jackson-friendly version of {@link Entry}.
 */
class JsonAdaptedEntry {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entry's %s field is missing!";

    private String text;
    private String date;
    private String time;
    private String feeling;
    private String weather;
    private String location;


    /**
     * Constructs a {@code JsonAdaptedEntry} with the given entry details.
     */
    @JsonCreator
    public JsonAdaptedEntry(@JsonProperty("text") String text, @JsonProperty("date") String date,
                            @JsonProperty("time") String time, @JsonProperty("feeling") String feeling,
                            @JsonProperty("weather") String weather,
                            @JsonProperty("location") String location) {
        this.text = text;
        this.date = date;
        this.time = time;
        this.feeling = feeling;
        this.weather = weather;
        this.location = location;
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedEntry(Entry source) {
        text = source.getText();
        date = source.getDateAsString();
        time = source.getTimeAsString();
        feeling = source.getFeeling().name();
        weather = source.getWeather().name();
        location = source.getLocationAsString();
    }

    /**
     * Converts this Jackson-friendly adapted entry object into the model's {@code Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entry.
     */
    public Entry toModelType() throws IllegalValueException {
        if (text == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Text"));
        }
        final String modelText = text;

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }

        DateTimeFormatter dateParser = DateTimeFormatter.ofPattern("dd MMM yyyy");
        final LocalDate modelDate = LocalDate.parse(date, dateParser);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Time"));
        }
        DateTimeFormatter timeParser = DateTimeFormatter.ofPattern("hh:mm a");
        final LocalTime modelTime = LocalTime.parse(time, timeParser);

        if (feeling == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Feeling"));
        }
        final Feeling modelFeeling = Feeling.valueOf(feeling);

        if (weather == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Weather"));
        }
        final Weather modelWeather = Weather.valueOf(weather);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Location"));
        }
        final Location modelLocation = new Location(location);

        Entry entry = new Entry(modelDate, modelTime);
        entry.setLocation(modelLocation);
        entry.setFeeling(modelFeeling);
        entry.setWeather(modelWeather);
        entry.setText(modelText);
        return entry;
    }
}
