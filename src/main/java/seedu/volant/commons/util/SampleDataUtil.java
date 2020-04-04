package seedu.volant.commons.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.model.TripList;
import seedu.volant.home.model.tag.Tag;
import seedu.volant.home.model.trip.DateRange;
import seedu.volant.home.model.trip.Location;
import seedu.volant.home.model.trip.Name;
import seedu.volant.home.model.trip.Trip;

/**
 * Contains utility methods for populating {@code TripList} with sample data.
 */
public class SampleDataUtil {
    public static Trip[] getSampleTrips() {
        return new Trip[] {
            new Trip(new Name("Graduation Trip"), new Location("Bali, Indonesia"),
                        new DateRange(LocalDate.parse("2020-06-05"), LocalDate.parse("2020-06-10"))),
            new Trip(new Name("Chinese New Year"), new Location("Hokkaido, Japan"),
                        new DateRange(LocalDate.parse("2020-01-25"), LocalDate.parse("2020-01-30"))),
            new Trip(new Name("Reading Week"), new Location("KL, Malaysia"),
                        new DateRange(LocalDate.parse("2020-04-01"), LocalDate.parse("2020-05-04"))),
        };
    }

    public static ReadOnlyTripList getSampleTripList() {
        TripList sampleAb = new TripList();
        for (Trip sampleTrip : getSampleTrips()) {
            sampleAb.addTrip(sampleTrip);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
