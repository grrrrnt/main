package seedu.volant.ui.pages.itinerary;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.ui.UiPart;
import seedu.volant.ui.pages.home.HomePage;


/**
 * Page containing details of a Trip as well as a menu of TripFeatures.
 */
public class ItineraryPage extends UiPart<Region> {
    private static final String FXML = "itinerary/ItineraryPage.fxml";
    private final Logger logger = LogsCenter.getLogger(HomePage.class);

    @FXML
    private Label itineraryTitle;

    @FXML
    private ListView<Activity> activityListView;

    public ItineraryPage(ObservableList<Activity> activities) {
        super(FXML);

        itineraryTitle.setText("ITINERARY");

        activityListView.setItems(activities);
        activityListView.setCellFactory(listView -> new ActivityListViewCell());
    }


    /**
     * Custom {@code ListCell} that displays the graphics of each {@code TripFeature} using a card depending on the
     * type of feature.
     */
    class ActivityListViewCell extends ListCell<Activity> {
        @Override
        protected void updateItem(Activity activity, boolean empty) {
            super.updateItem(activity, empty);
            if (empty || activity == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItineraryPageCard(activity, getIndex() + 1).getRoot());
            }
        }
    }
}
