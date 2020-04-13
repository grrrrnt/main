package seedu.volant.home.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.home.logic.commands.CommandTestUtil.DESC_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.DESC_GRADTRIP;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_DATEFROM_GRADTRIP;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_DATETO_GRADTRIP;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_LOCATION_GRADTRIP;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_TRIPNAME_GRADTRIP;

import org.junit.jupiter.api.Test;

import seedu.volant.testutil.EditTripDescriptorBuilder;

public class EditTripDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTripDescriptorBuilder descriptorWithSameValues =
                new EditTripDescriptorBuilder().withName(VALID_TRIPNAME_GRADTRIP)
                .withLocation(VALID_LOCATION_GRADTRIP).withDateRange(VALID_DATEFROM_GRADTRIP, VALID_DATETO_GRADTRIP);

        assertTrue(DESC_GRADTRIP.build().equals(descriptorWithSameValues.build()));

        // same object -> returns true
        assertTrue(DESC_GRADTRIP.equals(DESC_GRADTRIP));

        // null -> returns false
        assertFalse(DESC_GRADTRIP.equals(null));

        // different types -> returns false
        assertFalse(DESC_GRADTRIP.equals(5));

        // different values -> returns false
        assertFalse(DESC_GRADTRIP.equals(DESC_CNY));

    }
}
