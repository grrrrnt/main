package seedu.volant.home.logic.parser;

import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.volant.home.logic.commands.CommandTestUtil.DATERANGE_DESC_RECESS;
import static seedu.volant.home.logic.commands.CommandTestUtil.LOCATION_DESC_RECESS;
import static seedu.volant.home.logic.commands.CommandTestUtil.NAME_DESC_RECESS;
import static seedu.volant.home.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.volant.home.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.volant.testutil.TypicalTrips.B;

import org.junit.jupiter.api.Test;

import seedu.volant.home.logic.commands.AddCommand;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.testutil.TripBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    // TODO: Refine these tests

    @Test
    public void parse_allFieldsPresent_success() {
        Trip expectedTrip = new TripBuilder(B).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NAME_DESC_RECESS + LOCATION_DESC_RECESS + DATERANGE_DESC_RECESS,
                new AddCommand(expectedTrip));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_RECESS + LOCATION_DESC_RECESS + DATERANGE_DESC_RECESS,
                new AddCommand(expectedTrip));

        /*        // multiple phones - last phone accepted
                assertParseSuccess(parser, NAME_DESC_CNY + LOCATION_DESC_CNY + DATERANGE_DESC_CNY,
                        new AddCommand(expectedTrip));*/
    }

    /*    @Test
        public void parse_optionalFieldsMissing_success() {
            // zero tags
            Trip expectedTrip = new TripBuilder(A).build();
            assertParseSuccess(parser, NAME_DESC_GRADTRIP + LOCATION_DESC_GRADTRIP + DATERANGE_DESC_GRADTRIP,
                    new AddCommand(expectedTrip));
        }*/

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

    }

    @Test
    public void parse_invalidValue_failure() {

    }
}
