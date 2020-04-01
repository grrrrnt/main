package seedu.volant;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.volant.commons.core.Config;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.core.Version;
import seedu.volant.commons.exceptions.DataConversionException;
import seedu.volant.commons.logic.Logic;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.commons.storage.JsonUserPrefsStorage;
import seedu.volant.commons.storage.JsonVolantStorage;
import seedu.volant.commons.storage.Storage;
import seedu.volant.commons.storage.StorageManager;
import seedu.volant.commons.storage.UserPrefsStorage;
import seedu.volant.commons.storage.VolantStorage;
import seedu.volant.commons.util.ConfigUtil;
import seedu.volant.commons.util.SampleDataUtil;
import seedu.volant.commons.util.StringUtil;
import seedu.volant.home.logic.HomeLogicManager;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.model.TripList;
import seedu.volant.ui.Ui;
import seedu.volant.ui.UiManager;


/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected HomeModelManager model;
    protected Config config;
    protected ReadOnlyUserPrefs userPrefs;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing TripList ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        userPrefs = initPrefs(userPrefsStorage);

        VolantStorage volantStorage = new JsonVolantStorage(userPrefs.getVolantFilePath());
        storage = new StorageManager(volantStorage, userPrefsStorage);

        initLogging(config);

        /**
         * Initialising program with home logic manager.
         */
        model = initModelManager(storage, userPrefs);
        logic = new HomeLogicManager(model, storage);
        ui = new UiManager(logic);
    }

    /**
     * Returns application user preferences.
     */
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    /**
     * Returns a {@code HomeModelManager} with the data from {@code storage}'s trip list and {@code userPrefs}. <br>
     * The data from the sample trip list will be used instead if {@code storage}'s trip list is not found,
     * or an empty trip list will be used instead if errors occur when reading {@code storage}'s trip list.
     */
    private HomeModelManager initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTripList> tripListOptional;
        ReadOnlyTripList initialData;
        try {
            tripListOptional = storage.readTripList();
            if (!tripListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample trip list.");
            }

            initialData = tripListOptional.orElseGet(SampleDataUtil::getSampleTripList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty trip list.");
            initialData = new TripList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty trip list.");
            initialData = new TripList();
        }

        return new HomeModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty TripList");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Volant " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Volant ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
