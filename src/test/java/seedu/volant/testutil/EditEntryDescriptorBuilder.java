package seedu.volant.testutil;

import static seedu.volant.journal.logic.commands.EditCommand.EditEntryDescriptor;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.volant.home.model.trip.Location;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.Feeling;
import seedu.volant.journal.model.Weather;

/**
 * A utility class to help with building EditEntryDescriptor objects.
 */
public class EditEntryDescriptorBuilder {
    private EditEntryDescriptor descriptor;

    public EditEntryDescriptorBuilder() {
        descriptor = new EditEntryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEntryDescriptor} with fields containing {@code trip}'s details
     */
    public EditEntryDescriptorBuilder(Entry entry) {
        descriptor = new EditEntryDescriptor();
        descriptor.setText(entry.getText());
        descriptor.setLocation(entry.getLocation());
        descriptor.setDate(entry.getDate());
        descriptor.setTime(entry.getTime());
        descriptor.setFeeling(entry.getFeeling());
        descriptor.setWeather(entry.getWeather());
    }

    /**
     * Sets the {@code Name} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withText(String text) {
        descriptor.setText(text);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withDate(String date) {
        descriptor.setDate(LocalDate.parse(date));
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withTime(String time) {
        descriptor.setTime(LocalTime.parse(time));
        return this;
    }
    /**
     * Sets the {@code Feeling} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withFeeling(String feeling) {
        descriptor.setFeeling(Feeling.valueOf(feeling));
        return this;
    }

    /**
     * Sets the {@code Weather} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withWeather(String weather) {
        descriptor.setWeather(Weather.valueOf(weather));
        return this;
    }

    public EditEntryDescriptor build() {
        return descriptor;
    }
}
