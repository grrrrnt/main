package seedu.volant.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.Trip;

/**
 * A utility class containing a list of {@code Trip} objects to be used in tests.
 */
public class TypicalTrips {

    public static final Trip A = new TripBuilder().withName("America 2020")
            .withLocation("123, Jurong West Ave 6, #08-111")
            .withDateRange("2023-01-01", "2023-02-05").build();
    public static final Trip B = new TripBuilder().withName("Recess Week Solo Trip")
            .withLocation("Bali, Indonesia")
            .withDateRange("2024-02-25", "2024-02-28").build();
    public static final Trip C = new TripBuilder().withName("California Summer 2021")
            .withLocation("California, USA")
            .withDateRange("2021-06-01", "2021-06-20").build();
    public static final Trip D = new TripBuilder().withName("India Laa Bois")
            .withLocation("New Delhi, India")
            .withDateRange("2018-12-12", "2018-12-21").build();
    public static final Trip E = new TripBuilder().withName("Berlin berlin")
            .withLocation("Berlin, Germany")
            .withDateRange("2022-06-10", "2022-06-21").build();

    private TypicalTrips() {} // prevents instantiation

    /**
     * Returns an {@code TripList} with all the typical persons.
     */
    public static TripList getTypicalTripList() {
        TripList ab = new TripList();
        for (Trip trip : getTypicalTrips()) {
            ab.addTrip(trip);
        }
        return ab;
    }

    public static List<Trip> getTypicalTrips() {
        return new ArrayList<>(Arrays.asList(A, B, C, D));
    }

    public static Trip getGermanyTrip() {
        return E;
    }

    public static Trip getIndiaTrip() {
        return D;
    }
}
