package seedu.volant.trip.logic;

import java.nio.file.Path;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Logic;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.commons.storage.Storage;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.trip.logic.parser.TripInputParser;
import seedu.volant.trip.model.TripModelManager;

/**
 * The logic manager for the TRIP page.
 */
public class TripLogicManager implements Logic {

    private final TripModelManager model;
    private final Storage storage;
    private final TripInputParser inputParser;

    public TripLogicManager(TripModelManager model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.inputParser = new TripInputParser(model.getTrip());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        LOGGER.info("----------------[USER COMMAND IN TRIP][" + commandText + "]");

        CommandResult commandResult;
        Command command = inputParser.parseCommand(commandText);
        commandResult = command.execute(model);

        // No save operations as there are no operation to modify data in a trip page.

        return commandResult;
    }

    public TripModelManager getModel() {
        return null;
    }

    public Storage getStorage() {
        return storage;
    }

    public Trip getTrip() {
        return model.getTrip();
    }

    @Override
    public Path getVolantFilePath() {
        return model.getVolantFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
