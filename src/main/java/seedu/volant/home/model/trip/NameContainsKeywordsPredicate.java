package seedu.volant.home.model.trip;

import java.util.List;
import java.util.function.Predicate;

import seedu.volant.commons.util.StringUtil;

/**
 * Tests that a {@code Trip}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Trip> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Trip trip) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(trip.getName().tripName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return keywords.toString();
    }

}
