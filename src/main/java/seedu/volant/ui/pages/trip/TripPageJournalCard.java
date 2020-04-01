package seedu.volant.ui.pages.trip;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.volant.trip.model.TripFeature;
import seedu.volant.ui.UiPart;

/**
 * An UI component that displays information of a {@code Journal}.
 */
public class TripPageJournalCard extends UiPart<Region> {

    private static final String FXML = "trip/TripPageJournalCard.fxml";

    @FXML
    private HBox journalPane;

    @FXML
    private Label journalTitle;

    @FXML
    private Label numEntries;

    @FXML
    private ImageView journalCardIcon;

    public TripPageJournalCard(TripFeature journal) {
        super(FXML);

        Image journalCardImg = new Image("/images/journalIcon.png");
        journalCardIcon.setImage(journalCardImg);

        journalTitle.setText("Journal");
        numEntries.setText("You have " + journal.getNumItems() + " entries in your journal.");
    }

}
