package seedu.volant.itinerary.model.activity.util;

import java.util.List;
import java.util.function.Predicate;

import seedu.volant.commons.util.StringUtil;
import seedu.volant.itinerary.model.activity.Activity;

/**
 * Tests that a {@code Activity}'s {@code Location} matches any of the keywords given.
 */
public class LocationContainsKeywordsPredicate implements Predicate<Activity> {
    private final List<String> keywords;

    public LocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Activity activity) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(activity.getLocation().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LocationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LocationContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return keywords.toString();
    }

}
