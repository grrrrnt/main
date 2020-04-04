package seedu.volant.itinerary.model.activity.util;

import java.util.Comparator;

import seedu.volant.itinerary.model.activity.Activity;

/**
 * Comparator class that compares activities in activity list by date and time.
 */
public class DateTimeComparator implements Comparator<Activity> {

    @Override
    public int compare(Activity a1, Activity a2) {
        int dateCompareResult = a1.getDate().compareTo(a2.getDate());
        if (dateCompareResult == 0) {
            return a1.getTime().compareTo(a2.getTime());
        } else {
            return dateCompareResult;
        }
    }
}
