package seedu.volant.itinerary.logic.parser;

import static seedu.volant.home.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.DATE_DESC_SING;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.LOCATION_DESC_SING;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.TIME_DESC_SING;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.TITLE_DESC_SING;
import static seedu.volant.testutil.TypicalActivities.E;

import org.junit.jupiter.api.Test;

import seedu.volant.itinerary.logic.commands.AddCommand;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.testutil.ActivityBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Activity expectedActivity = new ActivityBuilder(E).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + TITLE_DESC_SING + DATE_DESC_SING + TIME_DESC_SING + LOCATION_DESC_SING,
                new AddCommand(expectedActivity));

        // multiple names - last name accepted
        assertParseSuccess(parser, TITLE_DESC_SING + DATE_DESC_SING + TIME_DESC_SING + LOCATION_DESC_SING,
                new AddCommand(expectedActivity));

    }
}
