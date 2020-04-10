package seedu.volant.testutil;

import java.time.LocalDate;

import seedu.volant.home.model.trip.DateRange;
import seedu.volant.home.model.trip.Location;
import seedu.volant.home.model.trip.Name;
import seedu.volant.home.model.trip.Trip;

/**
 * A utility class to help with building Trip objects.
 */
public class TripBuilder {

    public static final String DEFAULT_NAME = "Grad Trip 2020";
    public static final String DEFAULT_LOCATION = "Bali, Indonesia";
    public static final String DEFAULT_DATEFROM = "2020-02-01";
    public static final String DEFAULT_DATETO = "2020-02-06";

    private Name name;
    private Location location;
    private DateRange dateRange;

    public TripBuilder() {
        name = new Name(DEFAULT_NAME);
        location = new Location(DEFAULT_LOCATION);
        dateRange = new DateRange(LocalDate.parse(DEFAULT_DATEFROM), LocalDate.parse(DEFAULT_DATETO));
    }

    /**
     * Initializes the TripBuilder with the data of {@code tripToCopy}.
     */
    public TripBuilder(Trip tripToCopy) {
        name = tripToCopy.getName();
        location = tripToCopy.getLocation();
        dateRange = tripToCopy.getDateRange();
    }

    /**
     * Sets the {@code Name} of the {@code Trip} that we are building.
     */
    public TripBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Trip} that we are building.
     */
    public TripBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Trip} that we are building.
     */
    public TripBuilder withDateRange(String dateFrom, String dateTo) {
        this.dateRange = new DateRange(LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
        return this;
    }


    public Trip build() {
        return new Trip(name, location, dateRange);
    }

}
