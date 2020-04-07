package seedu.volant.itinerary.logic;

import java.io.IOException;
import java.nio.file.Path;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Logic;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.commons.storage.Storage;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.itinerary.logic.parser.ItineraryInputParser;
import seedu.volant.itinerary.model.ItineraryModelManager;

/**
 * The logic manager for the ITINERARY page.
 */
public class ItineraryLogicManager implements Logic {

    private final ItineraryModelManager model;
    private final Storage storage;
    private final ItineraryInputParser inputParser;

    public ItineraryLogicManager(ItineraryModelManager model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.inputParser = new ItineraryInputParser(model.getActivityList());
    }

    public ItineraryModelManager getModel() {
        return model;
    }

    public Storage getStorage() {
        return storage;
    }

    public Trip getTrip() {
        return model.getTrip();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        LOGGER.info("----------------[USER COMMAND IN ITINERARY][" + commandText + "]");

        CommandResult commandResult;
        Command command = inputParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveActivityList(model.getActivityList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
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
