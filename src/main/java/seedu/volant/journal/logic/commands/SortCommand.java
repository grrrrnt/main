package seedu.volant.journal.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.journal.model.util.SortType;

/**
 * Sorts EntryList based on given SortType.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts current entries based on a chosen order. \n"
            + "Parameters:\t" + "SORT_TYPE \n"
            + "Example:\t" + COMMAND_WORD + " FEELING"
            + "\nNOTE:\t\t"
            + "Available SORT_TYPEs: OLD, NEW, LOCATION, FEELING";

    public static final String MESSAGE_SORT_SUCCESS = "Entries sorted successfully by ";

    private final SortType sortType;

    public SortCommand(SortType sortType) {
        this.sortType = sortType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        JournalModelManager journalModel = ((JournalModelManager) model);

        journalModel.sortEntries(sortType);
        return new CommandResult(MESSAGE_SORT_SUCCESS + sortType.toString());
    }
}
