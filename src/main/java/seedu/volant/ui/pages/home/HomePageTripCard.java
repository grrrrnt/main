package seedu.volant.ui.pages.home;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.ui.UiPart;

/**
 * An UI component that displays information of a {@code Trip}.
 */
public class HomePageTripCard extends UiPart<Region> {
    private static final String FXML = "home/HomePageTripCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336"></a>
     */

    public final Trip trip;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label place;
    @FXML
    private Label dateRange;

    public HomePageTripCard(Trip trip, int displayedIndex) {
        super(FXML);
        this.trip = trip;
        id.setText(displayedIndex + " ");
        name.setText(trip.getName().tripName);
        place.setText(trip.getLocation().toString());
        dateRange.setText(trip.getDateRange().toString());
        cardPane.setMaxHeight(100);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HomePageTripCard)) {
            return false;
        }

        // state check
        HomePageTripCard card = (HomePageTripCard) other;
        return id.getText().equals(card.id.getText())
                && trip.equals(card.trip);
    }
}
