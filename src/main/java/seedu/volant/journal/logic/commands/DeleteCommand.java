package seedu.volant.journal.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.JournalModelManager;

/**
 * Deletes an entry identified using its displayed index from the journal.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the entry identified by the index number used in the journal."
            + "\nParameters:\tINDEX"
            + "\nExample:\t" + COMMAND_WORD + " 1"
            + "\nNOTE:\n"
            + "+ INDEX must be a positive integer within range of the number of entries in the journal.";

    public static final String MESSAGE_DELETE_ENTRY_SUCCESS = "Deleted entry: %1$s";

    public static final String MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX = "The entry index provided is invalid.";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        JournalModelManager journalModel = ((JournalModelManager) model);

        List<Entry> lastShownList = journalModel.getFilteredEntryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        Entry entryToDelete = lastShownList.get(targetIndex.getZeroBased());
        journalModel.deleteEntry(entryToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ENTRY_SUCCESS, entryToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
