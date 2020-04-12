package seedu.volant.itinerary.model.activity.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.volant.testutil.ActivityBuilder;

public class LocationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        LocationContainsKeywordsPredicate firstPredicate =
                new LocationContainsKeywordsPredicate(firstPredicateKeywordList);
        LocationContainsKeywordsPredicate secondPredicate =
                new LocationContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LocationContainsKeywordsPredicate firstPredicateCopy =
                new LocationContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different trip -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_locationContainsKeywords_returnsTrue() {
        // One keyword
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate(Collections.singletonList(
                "Frankfurt"));
        assertTrue(predicate.test(new ActivityBuilder().withLocation("Frankfurt Berlin").build()));

        // Multiple keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Frankfurt", "Berlin"));
        assertTrue(predicate.test(new ActivityBuilder().withLocation("Frankfurt Berlin").build()));

        // Only one matching keyword
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Berlin", "Carol"));
        assertTrue(predicate.test(new ActivityBuilder().withLocation("Frankfurt Carol").build()));

        // Mixed-case keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("FrANkfUrt", "bERlIn"));
        assertTrue(predicate.test(new ActivityBuilder().withLocation("Frankfurt Berlin").build()));
    }


    @Test
    public void test_locationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ActivityBuilder().withLocation("Frankfurt").build()));

        // Non-matching keyword
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Bremen"));
        assertFalse(predicate.test(new ActivityBuilder().withLocation("Frankfurt Berlin").build()));

        // Keywords match title, date and time, but does not match location
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Countdown", "12345",
                "25-12-2022", "19:00"));
        assertFalse(predicate.test(new ActivityBuilder().withLocation("Countdown")
                .withLocation("New York Time Square").withDate("2022-12-15").withTime("19:00").build()));
    }

}
