package seedu.volant.home.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.volant.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.logic.commands.ExitCommand;
import seedu.volant.commons.logic.commands.HelpCommand;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.home.logic.commands.AddCommand;
import seedu.volant.home.logic.commands.DeleteCommand;
import seedu.volant.home.logic.commands.EditCommand;
import seedu.volant.home.logic.commands.FindCommand;
import seedu.volant.home.model.trip.NameContainsKeywordsPredicate;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.testutil.EditTripDescriptorBuilder;
import seedu.volant.testutil.TripBuilder;
import seedu.volant.testutil.TripUtil;

public class LocationTripParserTest {

    private final HomeInputParser parser = new HomeInputParser();

    @Test
    public void parseCommand_add() throws Exception {
        Trip trip = new TripBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(TripUtil.getAddCommand(trip));
        assertEquals(new AddCommand(trip), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ITEM.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_ITEM), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Trip trip = new TripBuilder().build();
        EditCommand.EditTripDescriptor descriptor = new EditTripDescriptorBuilder(trip).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ITEM.getOneBased() + " " + TripUtil.getEditTripDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_ITEM, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
