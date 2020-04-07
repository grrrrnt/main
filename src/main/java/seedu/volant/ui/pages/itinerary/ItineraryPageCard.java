package seedu.volant.ui.pages.itinerary;

import static seedu.volant.commons.util.StringUtil.formatDate;
import static seedu.volant.commons.util.StringUtil.formatTime;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.ui.UiPart;
import seedu.volant.ui.pages.home.HomePageTripCard;

/**
 * An UI component that displays information of an {@code Activity}.
 */
public class ItineraryPageCard extends UiPart<Region> {
    private static final String FXML = "itinerary/ItineraryPageCard.fxml";

    private Activity activity;

    @FXML
    private HBox activityPane;

    @FXML
    private Label activityTitle;

    @FXML
    private Label activityTime;

    @FXML
    private Label activityDate;

    @FXML
    private Label activityLocation;

    @FXML
    private Label activityIndex;


    public ItineraryPageCard(Activity activity, int displayedIndex) {
        super(FXML);
        this.activity = activity;
        activityIndex.setText(Integer.toString(displayedIndex));
        activityTitle.setText(activity.getTitle().toString());
        activityDate.setText(formatDate(activity.getDate()));
        activityTime.setText(formatTime(activity.getTime()));
        activityLocation.setText(activity.getLocation().toString());
    }

    public Activity getActivity() {
        return activity;
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
        ItineraryPageCard card = (ItineraryPageCard) other;
        return activity.equals(card.activity);
    }
}
