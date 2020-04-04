package seedu.volant.itinerary.model.activity.util;

import java.time.LocalTime;
import java.util.function.Predicate;

import seedu.volant.itinerary.model.activity.Activity;

/**
 * Tests that a {@code Activity}'s {@code Time} matches any of the keywords given.
 */
public class TimeContainsKeywordsPredicate implements Predicate<Activity> {
    private final LocalTime keywords;

    public TimeContainsKeywordsPredicate(LocalTime keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Activity activity) {
        return activity.getTime().equals(keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TimeContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return keywords.toString();
    }

}
