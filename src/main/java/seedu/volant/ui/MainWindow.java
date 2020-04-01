package seedu.volant.ui;

import static seedu.volant.commons.logic.Page.HOME;
import static seedu.volant.commons.logic.Page.ITINERARY;
import static seedu.volant.commons.logic.Page.JOURNAL;
import static seedu.volant.commons.logic.Page.TRIP;

import java.nio.file.Paths;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.logic.Logic;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.logic.HomeLogicManager;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.itinerary.logic.ItineraryLogicManager;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.journal.logic.JournalLogicManager;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.trip.logic.TripLogicManager;
import seedu.volant.trip.model.Itinerary;
import seedu.volant.trip.model.Journal;
import seedu.volant.trip.model.TripFeature;
import seedu.volant.trip.model.TripModelManager;
import seedu.volant.ui.pages.home.HomePage;
import seedu.volant.ui.pages.itinerary.ItineraryPage;
import seedu.volant.ui.pages.journal.JournalPage;
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
    // Initialize current page = HOME
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

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
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


    /**
     * Handles the result of the command 'back'.
     * @param commandResult Contains list needed to populate the stage.
     */
    private void handleBack(CommandResult commandResult) {
        if (currentPage == TRIP) {
            handleGoToHome(commandResult.getTripList());
        }

        if (currentPage == ITINERARY || currentPage == JOURNAL) {
            handleGotoTrip(commandResult.getTrip());
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
        }

        // Going from TRIP page to TRIP_FEATURE page
        if (currentPage == TRIP) {
            handleGoToTripFeature(commandResult.getTripFeature());
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

    /** METHODS TO HANDLE CONTEXT SWITCHING **/

    public void setCurrentPage(Page page) {
        this.currentPage = page;
    }


    /**
     * Handles command to go to a TRIP page from the HOME page or TRIP_FEATURE page.
     * @param trip to navigate to.
     */
    @FXML
    public void handleGotoTrip(Trip trip) {
        TripList t;
        if (currentPage == ITINERARY) {
            t = ((ItineraryLogicManager) logic).getTripList();
        } else if (currentPage == JOURNAL) {
            t = ((JournalLogicManager) logic).getTripList();
        } else {
            t = ((HomeLogicManager) logic).getTripList();
        }

        logic.getStorage().setVolantFilePath(Paths.get("data", "volant.json"));
        logic = new TripLogicManager(new TripModelManager(t, trip, new UserPrefs()), logic.getStorage());

        mainPanelPlaceholder.getChildren().removeAll(mainPanel.getRoot()); // Remove GUI nodes from prev. display
        mainPanel = new TripPage(trip);
        mainPanelPlaceholder.getChildren().add(mainPanel.getRoot());
        setCurrentPage(TRIP);
    }

    /**
     * Handles command to go to a TRIP_FEATURE page from TRIP page.
     */
    @FXML
    public void handleGoToTripFeature(TripFeature tripFeature) {
        if (tripFeature instanceof Itinerary) {
            // TODO: Assign logic once logic for itinerary page has been created
            handleGoToItinerary(tripFeature);
        }

        if (tripFeature instanceof Journal) {
            handleGoToJournal(tripFeature);
        }
    }

    /**
     * Handles command to go to Itinerary from a Trip page
     */
    @FXML
    public void handleGoToItinerary(TripFeature tripFeature) {
        TripLogicManager t = ((TripLogicManager) logic);
        UserPrefs newUserPrefs = new UserPrefs();
        newUserPrefs.setVolantFilePath(Paths.get("data", t.getTrip().getName()
            + "/itinerary.json"));

        logic.getStorage().setVolantFilePath(Paths.get("data", t.getTrip().getName()
            + "/itinerary.json"));

        ItineraryModelManager itineraryModelManager = new ItineraryModelManager((TripList) t.getTripList(),
            t.getTrip(), (Itinerary) tripFeature, newUserPrefs, logic.getStorage());

        logic = new ItineraryLogicManager(itineraryModelManager, logic.getStorage());

        mainPanelPlaceholder.getChildren().removeAll(mainPanel.getRoot()); // Remove GUI nodes from prev. display
        ObservableList<Activity> activityObservableList = itineraryModelManager.getFilteredActivityList();
        mainPanel = new ItineraryPage(activityObservableList);
        mainPanelPlaceholder.getChildren().add(mainPanel.getRoot());
        setCurrentPage(ITINERARY);
    }

    /**
     * Handles command to go to Journal page from Trip page.
     */
    @FXML
    public void handleGoToJournal(TripFeature tripFeature) {
        TripLogicManager t = ((TripLogicManager) logic);

        UserPrefs newUserPrefs = new UserPrefs();
        newUserPrefs.setVolantFilePath(Paths.get("data", t.getTrip().getName()
            + "/journal.json"));

        logic.getStorage().setVolantFilePath(Paths.get("data", t.getTrip().getName()
            + "/journal.json"));

        JournalModelManager journalModelManager = new JournalModelManager((TripList) t.getTripList(),
            t.getTrip(), (Journal) tripFeature, newUserPrefs, logic.getStorage());

        logic = new JournalLogicManager(journalModelManager, logic.getStorage());

        mainPanelPlaceholder.getChildren().removeAll(mainPanel.getRoot()); // Remove GUI nodes from prev. display
        mainPanel = new JournalPage(journalModelManager.getFilteredEntryList());
        mainPanelPlaceholder.getChildren().add(mainPanel.getRoot());
        setCurrentPage(JOURNAL);
    }

    /**
     * Handles command to go to HOME page from any page.
     */
    @FXML
    public void handleGoToHome(TripList tripList) {
        UserPrefs newUserPrefs = new UserPrefs();
        newUserPrefs.setVolantFilePath(Paths.get("data", "volant.json"));

        HomeModelManager homeModelManager = new HomeModelManager(tripList, newUserPrefs);

        logic.getStorage().setVolantFilePath(Paths.get("data", "volant.json"));
        logic = new HomeLogicManager(homeModelManager, logic.getStorage());

        mainPanelPlaceholder.getChildren().removeAll(mainPanel.getRoot()); // Remove GUI nodes from prev. display
        mainPanel = new HomePage(homeModelManager.getFilteredTripList());
        mainPanelPlaceholder.getChildren().add(mainPanel.getRoot());
        setCurrentPage(HOME);
    }

    /**
     * Handles the result of the command to manage the stage.
     * @param commandResult Contains the result to be handled.
     */
    private void handleResult(CommandResult commandResult) {
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
            handleGoToHome(commandResult.getModel().getTripList());
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


            if (currentPage == HOME) {
                HomeModelManager currentModel = ((HomeLogicManager) logic).getModel();
                mainPanelPlaceholder.getChildren().removeAll(mainPanel.getRoot());
                mainPanel = new HomePage(currentModel.getFilteredTripList());
                mainPanelPlaceholder.getChildren().add(mainPanel.getRoot());
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
