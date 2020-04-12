package seedu.volant.itinerary.model.activity.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.volant.testutil.ActivityBuilder;

public class TimeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        LocalTime firstPredicateKeyword = LocalTime.parse("12:00");
        LocalTime secondPredicateKeyword = LocalTime.parse("14:00");

        TimeContainsKeywordsPredicate firstPredicate = new TimeContainsKeywordsPredicate(firstPredicateKeyword);
        TimeContainsKeywordsPredicate secondPredicate = new TimeContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TimeContainsKeywordsPredicate firstPredicateCopy = new TimeContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Time -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_timeContainsKeywords_returnsTrue() {
        // One keyword
        TimeContainsKeywordsPredicate predicate = new TimeContainsKeywordsPredicate(LocalTime.parse("12:00"));
        assertTrue(predicate.test(new ActivityBuilder().withTime("12:00").build()));
    }


    @Test
    public void test_timeDoesNotContainKeyword_returnsFalse() {
        // Zero keywords
        TimeContainsKeywordsPredicate predicate = new TimeContainsKeywordsPredicate(null);
        assertFalse(predicate.test(new ActivityBuilder().withTime("12:00").build()));

        // Non-matching keyword
        predicate = new TimeContainsKeywordsPredicate(LocalTime.parse("14:00"));
        assertFalse(predicate.test(new ActivityBuilder().withTime("12:00").build()));
    }

}
