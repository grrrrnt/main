package seedu.volant.home.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.volant.commons.core.Messages;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.trip.NameContainsKeywordsPredicate;
import seedu.volant.home.model.trip.Trip;

/**
 * Finds and lists all trips in trip list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all trips whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " bali singapore paris";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        HomeModelManager homeModel = ((HomeModelManager) model);
        ObservableList<Trip> currentList = homeModel.getFilteredTripList();

        homeModel.updateFilteredTripList(predicate);

        if (homeModel.getFilteredTripList().isEmpty()) {
            homeModel.updateFilteredTripList(homeModel.getPredicateShowAllTrips());
            return new CommandResult(
                String.format("No trips with this keyword(s): "
                    + predicate.toString() + " found!", currentList.size()));
        } else {
            return new CommandResult(
                String.format(Messages.MESSAGE_TRIPS_LISTED_OVERVIEW, homeModel.getFilteredTripList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
