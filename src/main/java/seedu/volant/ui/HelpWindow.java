package seedu.volant.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.logic.commands.ExitCommand;
import seedu.volant.commons.logic.commands.RefreshCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay1920s2-cs2103t-f09-4.github.io/main/UserGuide.html";
    public static final String HELP_MESSAGE = "For a more detailed explanation, please refer to the user guide\n"
            + USERGUIDE_URL;

    protected static final String WELCOME_TEMP = "Hello! Welcome to the help window for the %s page!"
            + "\n\nHere are some commands that you can try on this page: \n\n";

    protected static final String COMMON_COMMANDS = RefreshCommand.MESSAGE_USAGE + "\n\n"
            + ExitCommand.MESSAGE_USAGE + "\n\n";

    protected static String welcome = "";

    protected static String commands = "";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";



    @FXML
    protected Label helpWindowHeader;

    @FXML
    private Scene scene;

    @FXML
    private Label moreInfo;

    @FXML
    private TextArea textArea;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        root.setResizable(true);
        root.setMinWidth(1000);
        scene.getStylesheets().add("view/VolantTheme.css");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setText(commands);
        moreInfo.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
