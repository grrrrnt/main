package seedu.volant.journal.logic;

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
import seedu.volant.journal.logic.parser.JournalInputParser;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.trip.model.Journal;

/**
 * The logic manager for the JOURNAL page.
 */
public class JournalLogicManager implements Logic {

    private final JournalModelManager model;
    private final Storage storage;
    private final JournalInputParser inputParser;

    public JournalLogicManager(JournalModelManager model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.inputParser = new JournalInputParser(model.getJournal());
    }

    public JournalModelManager getModel() {
        return model;
    }

    public Storage getStorage() {
        return storage;
    }

    public Trip getTrip() {
        return model.getTrip();
    }

    public Journal getJournal() {
        return model.getJournal();
    }


    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        LOGGER.info("----------------[USER COMMAND IN JOURNAL][" + commandText + "]");

        CommandResult commandResult;
        Command command = inputParser.parseCommand(commandText);
        commandResult = command.execute(model);
        System.out.println(storage.getVolantFilePath());
        try {
            storage.saveEntryList(model.getEntryList());
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
