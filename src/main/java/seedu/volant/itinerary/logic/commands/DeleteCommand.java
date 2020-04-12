package seedu.volant.itinerary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.volant.commons.core.Messages;
import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;

/**
 * Deletes an activity in the itinerary identified using it's displayed index from the address book.
 */

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the activity identified by the index number used in the displayed itinerary."
            + "\nParameters:\tINDEX"
            + "\nExample:\t" + COMMAND_WORD + " 1"
            + "\nNOTE:\n"
            + "+ INDEX must be a positive integer within range of the itinerary size.";

    public static final String MESSAGE_DELETE_ACTIVITY_SUCCESS = "Deleted activity: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ItineraryModelManager itineraryModel = ((ItineraryModelManager) model);

        List<Activity> lastShownList = itineraryModel.getFilteredActivityList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
        }

        Activity activityToDelete = lastShownList.get(targetIndex.getZeroBased());
        itineraryModel.deleteActivity(activityToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
