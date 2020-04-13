package seedu.volant.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.Feeling;
import seedu.volant.journal.model.Weather;

/**
 * A utility class to help with building Entry objects.
 */
public class EntryBuilder {
    public static final String DEFAULT_DATE = "2020-04-02";
    public static final String DEFAULT_TIME = "12:51";
    public static final String DEFAULT_TEXT = "I am testing out Volant's journal!";
    public static final String DEFAULT_FEELING = "EXCITED";
    public static final String DEFAULT_WEATHER = "SUNNY";
    public static final String DEFAULT_LOCATION = "Tembusu College";

    private String text;
    private String location;
    private LocalDate date;
    private LocalTime time;
    private Feeling feeling;
    private Weather weather;

    public EntryBuilder() {
        text = DEFAULT_TEXT;
        location = DEFAULT_LOCATION;
        date = LocalDate.parse(DEFAULT_DATE);
        time = LocalTime.parse(DEFAULT_TIME);
        feeling = Feeling.valueOf(DEFAULT_FEELING);
        weather = Weather.valueOf(DEFAULT_WEATHER);
    }

    /**
     * Initializes the Journal with the data of {@code tripToCopy}.
     */
    public EntryBuilder(Entry toCopy) {
        text = toCopy.getText();
        location = toCopy.getLocationAsString();
        date = toCopy.getDate();
        time = toCopy.getTime();
        feeling = toCopy.getFeeling();
        weather = toCopy.getWeather();
    }

    /**
     * Sets the {@code Text} of the {@code Entry} that we are building.
     */
    public EntryBuilder withText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Entry} that we are building.
     */
    public EntryBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Entry} that we are building.
     */
    public EntryBuilder withDate(String date) {
        this.date = LocalDate.parse(date);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Entry} that we are building.
     */
    public EntryBuilder withTime(String time) {
        this.time = LocalTime.parse(time);
        return this;
    }

    /**
     * Sets the {@code Feeling} of the {@code Entry} that we are building.
     */
    public EntryBuilder withFeeling(String feeling) {
        this.feeling = Feeling.valueOf(feeling);
        return this;
    }

    /**
     * Sets the {@code Weather} of the {@code Entry} that we are building.
     */
    public EntryBuilder withWeather(String weather) {
        this.weather = Weather.valueOf(weather);
        return this;
    }

    /**
     * Build the Entry object
     * @return the Entry object that has been built
     */
    public Entry build() {
        Entry entry = new Entry(date, time, text, feeling, weather, location);
        return entry;
    }
}
