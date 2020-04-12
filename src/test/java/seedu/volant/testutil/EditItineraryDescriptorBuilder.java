package seedu.volant.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.volant.home.model.trip.Location;
import seedu.volant.itinerary.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.itinerary.model.activity.Title;

/**
 * A utility class to help with building EditItineraryDescriptor objects.
 */
public class EditItineraryDescriptorBuilder {

    private EditItineraryDescriptor descriptor;

    public EditItineraryDescriptorBuilder() {
        descriptor = new EditItineraryDescriptor();
    }

    public EditItineraryDescriptorBuilder(EditItineraryDescriptor descriptor) {
        this.descriptor = new EditItineraryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code trip}'s details
     */
    public EditItineraryDescriptorBuilder(Activity activity) {
        descriptor = new EditItineraryDescriptor();
        descriptor.setTitle(activity.getTitle());
        descriptor.setLocation(activity.getLocation());
        descriptor.setDate(activity.getDate());
        descriptor.setTime(activity.getTime());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code DateRange} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withDate(String date) {
        descriptor.setDate(LocalDate.parse(date));
        return this;
    }

    /**
     * Sets the {@code DateRange} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withTime(String time) {
        descriptor.setTime(LocalTime.parse(time));
        return this;
    }

    public EditItineraryDescriptor build() {
        return descriptor;
    }
}
