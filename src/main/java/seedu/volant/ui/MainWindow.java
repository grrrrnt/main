package seedu.volant.ui;

import static seedu.volant.commons.logic.Page.HOME;
import static seedu.volant.commons.logic.Page.ITINERARY;
import static seedu.volant.commons.logic.Page.JOURNAL;
import static seedu.volant.commons.logic.Page.TRIP;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.exceptions.DataConversionException;
import seedu.volant.commons.logic.Logic;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.RefreshCommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.commons.util.SampleDataUtil;
import seedu.volant.home.logic.HomeLogicManager;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.itinerary.logic.ItineraryLogicManager;
import seedu.volant.itinerary.model.ActivityList;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.ReadOnlyActivityList;
import seedu.volant.journal.logic.JournalLogicManager;
import seedu.volant.journal.model.EntryList;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.journal.model.ReadOnlyEntryList;
import seedu.volant.trip.logic.TripLogicManager;
import seedu.volant.trip.model.Itinerary;
import seedu.volant.trip.model.Journal;
import seedu.volant.trip.model.TripFeature;
import seedu.volant.trip.model.TripFeatureList;
import seedu.volant.trip.model.TripModelManager;
import seedu.volant.ui.pages.home.HomeHelpWindow;
import seedu.volant.ui.pages.home.HomePage;
import seedu.volant.ui.pages.itinerary.ItineraryHelpWindow;
import seedu.volant.ui.pages.itinerary.ItineraryPage;
import seedu.volant.ui.pages.journal.JournalHelpWindow;
import seedu.volant.ui.pages.journal.JournalPage;
import seedu.volant.ui.pages.trip.TripHelpWindow;
import seedu.volant.ui.pages.trip.TripPage;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Initialize current page = HOME when program starts
    private Page currentPage = HOME;


    // Independent Ui parts residing in this Ui container
    // mainPanel is where the context switching happens
    private UiPart<Region> mainPanel;

    private ResultDisplay resultDisplay;

    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem refreshMenuItem;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private StackPane mainPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // primaryStage.setMaxHeight(794);
        primaryStage.setMinWidth(750);
        primaryStage.setMinHeight(688);

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
        setAccelerators();

        // Initialize with home help window
        helpWindow = new HomeHelpWindow();
        scrollPane.setFitToWidth(true);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Sets the key bindings for the application.
     * Note: When user is using key bindings, there will be no result display.
     */
    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(refreshMenuItem, KeyCombination.valueOf("F5"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        HomeLogicManager t = ((HomeLogicManager) logic);
        mainPanel = new HomePage(t.getFilteredTripList());
        mainPanelPlaceholder.getChildren().add(mainPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getVolantFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        helpWindow = new HomeHelpWindow();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    void show() {
        primaryStage.show();
    }

    /** METHODS TO HANDLE CONTEXT SWITCHING **/

    public void setCurrentPage(Page page) {
        this.currentPage = page;
        if (currentPage == HOME) {
            helpWindow = new HomeHelpWindow();
        }

        if (currentPage == TRIP) {
            helpWindow = new TripHelpWindow();
        }

        if (currentPage == ITINERARY) {
            helpWindow = new ItineraryHelpWindow();
        }

        if (currentPage == JOURNAL) {
            helpWindow = new JournalHelpWindow();
        }
    }

    /**
     * Handles the result of the command 'back'.
     * @param commandResult Contains list needed to populate the stage.
     */
    private void handleBack(CommandResult commandResult) {
        if (currentPage == TRIP) {
            handleGoToHome();
            setCurrentPage(HOME);
        }

        if (currentPage == ITINERARY || currentPage == JOURNAL) {
            handleGotoTrip(commandResult.getTrip());
            setCurrentPage(TRIP);
        }
    }

    /**
     * Handles the result of the 'goto' command.
     * @param commandResult Contains list needed to populate the stage.
     */
    private void handleGoto(CommandResult commandResult) {
        // Going from HOME page to TRIP page
        if (currentPage == HOME) {
            handleGotoTrip(commandResult.getTrip());
            setCurrentPage(TRIP);
        }

        // Going from TRIP page to TRIP_FEATURE page
        if (currentPage == TRIP) {
            handleGoToTripFeature(commandResult.getTripFeature());
            if (commandResult.getTripFeature() instanceof Itinerary) {
                setCurrentPage(ITINERARY);
            }
            if (commandResult.getTripFeature() instanceof Journal) {
                setCurrentPage(JOURNAL);
            }
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
            (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    private void updateStatusBar() {
        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getVolantFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
    }

    /**
     * Handles command to go to a TRIP page from the HOME page or TRIP_FEATURE page.
     * @param trip to navigate to.
     */
    @FXML
    public void handleGotoTrip(Trip trip) {
        UserPrefs newUserPrefs = new UserPrefs();
        newUserPrefs.setVolantFilePath(Paths.get("data", trip.getName().toString()));
        fetchTripFeatures(trip);
        logic.getStorage().setVolantFilePath(Paths.get("data", trip.getName().toString()));
        logic = new TripLogicManager(new TripModelManager(trip, newUserPrefs), logic.getStorage());

        switchView(new TripPage(trip));
        updateStatusBar();
        setCurrentPage(TRIP);
    }

    /**
     * Updates trip with data of Journal and Itinerary features
     * @param trip trip to be updated.
     */
    private void fetchTripFeatures(Trip trip) {
        ActivityList activityList;
        EntryList entryList;
        try {
            logic.getStorage().setVolantFilePath(Paths.get("data", trip.getName() + "/itinerary.json"));
            Optional<ReadOnlyActivityList> activityListOptional = logic.getStorage().readActivityList();
            activityList = new ActivityList(activityListOptional.get());
        } catch (IOException | DataConversionException | NoSuchElementException e) {
            activityList = new ActivityList();
            entryList = new EntryList();
        }

        try {
            logic.getStorage().setVolantFilePath(Paths.get("data", trip.getName() + "/journal.json"));
            Optional<ReadOnlyEntryList> entryListOptional = logic.getStorage().readEntryList();
            entryList = new EntryList(entryListOptional.get());
        } catch (IOException | DataConversionException | NoSuchElementException e) {
            entryList = new EntryList();
        }

        trip.setTripFeatureList(new TripFeatureList(new Itinerary(activityList), new Journal(entryList)));
    }

    /**
     * Handles command to go to a TRIP_FEATURE page from TRIP page.
     */
    @FXML
    public void handleGoToTripFeature(TripFeature tripFeature) {
        if (tripFeature instanceof Itinerary) {
            handleGoToItinerary();
        }

        if (tripFeature instanceof Journal) {
            handleGoToJournal();
        }
    }

    /**
     * Handles command to go to Itinerary from a Trip page
     */
    @FXML
    public void handleGoToItinerary() {
        TripLogicManager t = ((TripLogicManager) logic);
        UserPrefs newUserPrefs = new UserPrefs();
        newUserPrefs.setVolantFilePath(Paths.get("data", t.getTrip().getName()
            + "/itinerary.json"));

        logic.getStorage().setVolantFilePath(Paths.get("data", t.getTrip().getName()
            + "/itinerary.json"));

        ItineraryModelManager itineraryModelManager = new ItineraryModelManager(t.getTrip(),
                newUserPrefs);

        logic = new ItineraryLogicManager(itineraryModelManager, logic.getStorage());

        switchView(new ItineraryPage(itineraryModelManager.getFilteredActivityList()));
        updateStatusBar();
        setCurrentPage(ITINERARY);
    }

    /**
     * Handles command to go to Journal page from Trip page.
     */
    @FXML
    public void handleGoToJournal() {
        TripLogicManager t = ((TripLogicManager) logic);

        UserPrefs newUserPrefs = new UserPrefs();
        newUserPrefs.setVolantFilePath(Paths.get("data", t.getTrip().getName()
            + "/journal.json"));

        logic.getStorage().setVolantFilePath(Paths.get("data", t.getTrip().getName()
            + "/journal.json"));

        JournalModelManager journalModelManager = new JournalModelManager(t.getTrip(),
                newUserPrefs);

        logic = new JournalLogicManager(journalModelManager, logic.getStorage());

        switchView(new JournalPage(journalModelManager.getFilteredEntryList()));
        updateStatusBar();
        setCurrentPage(JOURNAL);
    }

    private ReadOnlyTripList getTripList() {
        Optional<ReadOnlyTripList> tripListOptional;
        ReadOnlyTripList tripList;
        try {
            tripListOptional = logic.getStorage().readTripList();
            if (!tripListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample trip list.");
            }

            tripList = tripListOptional.orElseGet(SampleDataUtil::getSampleTripList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty trip list.");
            tripList = new TripList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty trip list.");
            tripList = new TripList();
        }
        return tripList;
    }

    /**
     * Handles command to go to HOME page from any page.
     */
    @FXML
    public void handleGoToHome() {
        UserPrefs newUserPrefs = new UserPrefs();
        newUserPrefs.setVolantFilePath(Paths.get("data", "volant.json"));
        logic.getStorage().setVolantFilePath(Paths.get("data", "volant.json"));
        HomeModelManager homeModelManager = new HomeModelManager(getTripList(), newUserPrefs);
        logic = new HomeLogicManager(homeModelManager, logic.getStorage());

        switchView(new HomePage(homeModelManager.getFilteredTripList()));
        updateStatusBar();
        setCurrentPage(HOME);
    }

    /**
     * Handles refreshing a page.
     */
    @FXML
    private void handleRefresh() throws CommandException, ParseException {
        if (currentPage == HOME) {
            HomeModelManager currentModel = ((HomeLogicManager) logic).getModel();
            currentModel.updateFilteredTripList(currentModel.getPredicateShowAllTrips());
            switchView(new HomePage(currentModel.getFilteredTripList()));
        }

        if (currentPage == ITINERARY) {
            ItineraryModelManager currentModel = ((ItineraryLogicManager) logic).getModel();
            switchView(new ItineraryPage(currentModel.getActivityList().getActivityList()));
        }

        if (currentPage == JOURNAL) {
            // Upon refreshing journal page, revert journal page to sorting by NEW
            executeCommand("sort NEW");
        }
    }

    /**
     * Switches view of mainPanel to {@param view}.
     */
    private void switchView(UiPart<Region> view) {
        mainPanelPlaceholder.getChildren().remove(mainPanel.getRoot());
        mainPanel = view;
        mainPanelPlaceholder.getChildren().add(mainPanel.getRoot());
    }

    /**
     * Handles the result of the command to manage the stage.
     * @param commandResult Contains the result to be handled.
     */
    private void handleResult(CommandResult commandResult) throws CommandException, ParseException {
        if (commandResult.isShowHelp()) {
            handleHelp();
        }

        if (commandResult.isExit()) {
            handleExit();
        }

        if (commandResult.isGoto()) {
            handleGoto(commandResult);
        }

        if (commandResult.isBack()) {
            handleBack(commandResult);
        }

        if (commandResult.isHome()) {
            handleGoToHome();
        }

        if (commandResult instanceof RefreshCommandResult) {
            handleRefresh();
        }
    }

    /**
     * Executes the command and returns the result.
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            /*If the command is something that alters the trip list, page will be refreshed after execution*/
            if (currentPage == HOME) {
                HomeModelManager currentModel = ((HomeLogicManager) logic).getModel();
                switchView(new HomePage(currentModel.getFilteredTripList()));
            }

            handleResult(commandResult);
            return commandResult;

        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}
