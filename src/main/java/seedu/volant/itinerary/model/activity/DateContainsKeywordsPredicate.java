package seedu.volant.itinerary.model.activity;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Activity}'s {@code Date} matches any of the keywords given.
 */
public class DateContainsKeywordsPredicate implements Predicate<Activity> {
    private final LocalDate keywords;

    public DateContainsKeywordsPredicate(LocalDate keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Activity activity) {
        return activity.getDate().isEqual(keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DateContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return keywords.toString();
    }

}
