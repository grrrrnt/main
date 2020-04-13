package seedu.volant.itinerary.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.DESC_ITINERARY_SING;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.DESC_ITINERARY_ZOO;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.VALID_DATE_ZOO;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.VALID_LOCATION_ZOO;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.VALID_TIME_ZOO;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.VALID_TITLE_ZOO;

import org.junit.jupiter.api.Test;

import seedu.volant.testutil.EditItineraryDescriptorBuilder;

public class EditItineraryDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditItineraryDescriptorBuilder descriptorWithSameValues =
                new EditItineraryDescriptorBuilder().withTitle(VALID_TITLE_ZOO)
                        .withLocation(VALID_LOCATION_ZOO).withDate(VALID_DATE_ZOO)
                .withTime(VALID_TIME_ZOO);

        assertTrue(DESC_ITINERARY_ZOO.build().equals(descriptorWithSameValues.build()));

        // same object -> returns true
        assertTrue(DESC_ITINERARY_ZOO.equals(DESC_ITINERARY_ZOO));

        // null -> returns false
        assertFalse(DESC_ITINERARY_ZOO.equals(null));

        // different types -> returns false
        assertFalse(DESC_ITINERARY_ZOO.equals(5));

        // different values -> returns false
        assertFalse(DESC_ITINERARY_ZOO.equals(DESC_ITINERARY_SING));

    }
}
