package seedu.volant.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.volant.itinerary.model.ActivityList;
import seedu.volant.itinerary.model.activity.Activity;

/**
 * A utility class containing a list of {@code Activities} objects to be used in tests.
 */
public class TypicalActivities {

    public static final Activity A = new ActivityBuilder().withTitle("Visit the War Museum")
            .withLocation("Berlin, Germany")
            .withDate("2022-06-11")
            .withTime("20:00").build();
    public static final Activity B = new ActivityBuilder().withTitle("Eat some McDonald Chicken nuggets")
            .withLocation("Berlin, Germany")
            .withDate("2022-06-12")
            .withTime("02:00").build();
    public static final Activity C = new ActivityBuilder().withTitle("Build a snowman")
            .withLocation("Berlin, Germany")
            .withDate("2022-06-13")
            .withTime("16:30").build();
    public static final Activity D = new ActivityBuilder().withTitle("Visit the Brandenburg Gate")
            .withLocation("Berlin, Germany")
            .withDate("2022-06-14")
            .withTime("10:30").build();

    private TypicalActivities() {} // prevents instantiation

    /**
     * Returns an {@code ActivityList} with all the typical activities.
     */
    public static ActivityList getTypicalActivityList() {
        ActivityList list = new ActivityList();
        for (Activity activity : getTypicalActivities()) {
            list.addActivity(activity);
        }
        return list;
    }

    public static List<Activity> getTypicalActivities() {
        return new ArrayList<>(Arrays.asList(A, B, C, D));
    }
}
