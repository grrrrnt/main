package seedu.volant.testutil;

import java.time.LocalDate;

import seedu.volant.home.logic.commands.EditCommand.EditTripDescriptor;
import seedu.volant.home.model.trip.DateRange;
import seedu.volant.home.model.trip.Location;
import seedu.volant.home.model.trip.Name;
import seedu.volant.home.model.trip.Trip;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditTripDescriptorBuilder {

    private EditTripDescriptor descriptor;

    public EditTripDescriptorBuilder() {
        descriptor = new EditTripDescriptor();
    }

    public EditTripDescriptorBuilder(EditTripDescriptor descriptor) {
        this.descriptor = new EditTripDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code trip}'s details
     */
    public EditTripDescriptorBuilder(Trip trip) {
        descriptor = new EditTripDescriptor();
        descriptor.setName(trip.getName());
        descriptor.setLocation(trip.getLocation());
        descriptor.setDateRange(trip.getDateRange());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code DateRange} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withDateRange(String dateFrom, String dateTo) {
        descriptor.setDateRange(new DateRange(LocalDate.parse(dateFrom), LocalDate.parse(dateTo)));
        return this;
    }

    public EditTripDescriptor build() {
        return descriptor;
    }

}
