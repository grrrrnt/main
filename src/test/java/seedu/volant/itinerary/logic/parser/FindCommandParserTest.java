package seedu.volant.itinerary.logic.parser;

import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.volant.home.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.volant.itinerary.logic.commands.CommandTestUtil.VALID_DATE_SING;

import org.junit.jupiter.api.Test;

import seedu.volant.itinerary.logic.commands.FindCommand;

public class FindCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no prefix specified
        assertParseFailure(parser, VALID_DATE_SING, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, PREFIX_TITLE + "", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }
}
