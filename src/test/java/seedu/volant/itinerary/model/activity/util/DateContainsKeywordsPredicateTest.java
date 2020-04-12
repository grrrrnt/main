package seedu.volant.itinerary.model.activity.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.volant.testutil.ActivityBuilder;

public class DateContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        LocalDate firstPredicateKeyword = LocalDate.parse("2022-05-01");
        LocalDate secondPredicateKeyword = LocalDate.parse("2022-06-14");

        DateContainsKeywordsPredicate firstPredicate = new DateContainsKeywordsPredicate(firstPredicateKeyword);
        DateContainsKeywordsPredicate secondPredicate = new DateContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DateContainsKeywordsPredicate firstPredicateCopy = new DateContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Date -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_dateContainsKeywords_returnsTrue() {
        // One keyword
        DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(LocalDate.parse("2022-05-01"));
        assertTrue(predicate.test(new ActivityBuilder().withDate("2022-05-01").build()));
    }


    @Test
    public void test_dateDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(LocalDate.parse("2022-06-14"));
        assertFalse(predicate.test(new ActivityBuilder().withDate("2022-05-01").build()));
    }

}
