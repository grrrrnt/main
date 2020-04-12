package seedu.volant.itinerary.model.activity.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.volant.testutil.ActivityBuilder;

public class TitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TitleContainsKeywordsPredicate firstPredicate = new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        TitleContainsKeywordsPredicate secondPredicate = new TitleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleContainsKeywordsPredicate firstPredicateCopy =
                new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different title -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(Collections.singletonList(
                "Parasailing"));
        assertTrue(predicate.test(new ActivityBuilder().withTitle("Parasailing Scubadiving").build()));

        // Multiple keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Parasailing", "Scubadiving"));
        assertTrue(predicate.test(new ActivityBuilder().withTitle("Parasailing Scubadiving").build()));

        // Only one matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Scubadiving", "Skiing"));
        assertTrue(predicate.test(new ActivityBuilder().withTitle("Parasailing Skiing").build()));

        // Mixed-case keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("PArASaIliNG", "SCUbaDiVINg"));
        assertTrue(predicate.test(new ActivityBuilder().withTitle("Parasailing Scubadiving").build()));
    }


    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ActivityBuilder().withTitle("Parasailing").build()));

        // Non-matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Skiing"));
        assertFalse(predicate.test(new ActivityBuilder().withTitle("Parasailing Scubadiving").build()));

        // Keywords match location, date and time, but does not match title
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Trekking", "Hebei", "25-12-2022",
                "19:00"));
        assertFalse(predicate.test(new ActivityBuilder().withTitle("Parasailing").withLocation("Hebei")
                .withDate("2022-12-15").withTime("19:00").build()));
    }

}
