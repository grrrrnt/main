package seedu.volant.ui.pages.trip;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.trip.model.Journal;
import seedu.volant.trip.model.TripFeature;
import seedu.volant.ui.UiPart;
import seedu.volant.ui.pages.home.HomePage;


/**
 * Page containing details of a Trip as well as a menu of TripFeatures.
 */
public class TripPage extends UiPart<Region> {
    private static final String FXML = "trip/TripPage.fxml";
    private final Logger logger = LogsCenter.getLogger(HomePage.class);

    @FXML
    private Label tripName;

    @FXML
    private Label place;

    @FXML
    private Label dateRange;

    @FXML
    private ListView<TripFeature> tripFeaturesListView;

    public TripPage(Trip trip) {
        super(FXML);
        tripName.setText(trip.getName().toString());
        place.setText(trip.getLocation().toString());
        dateRange.setText(trip.getDateRange().toString());

        tripFeaturesListView.setPrefHeight(300);

        tripFeaturesListView.setItems(trip.getTripFeatureList().getReadOnlyFeatureList());
        tripFeaturesListView.setCellFactory(listView -> new TripFeaturesListViewCell());
    }


    /**
     * Custom {@code ListCell} that displays the graphics of each {@code TripFeature} using a card depending on the
     * type of feature.
     */
    class TripFeaturesListViewCell extends ListCell<TripFeature> {
        @Override
        protected void updateItem(TripFeature tripFeature, boolean empty) {
            super.updateItem(tripFeature, empty);

            if (empty || tripFeature == null) {
                setStyle("-fx-background-color: #fff;");
            } else {
                if (tripFeature instanceof Journal) {
                    setGraphic(new TripPageJournalCard(tripFeature).getRoot());
                } else {
                    setGraphic(new TripPageItineraryCard(tripFeature).getRoot());
                }
            }
        }
    }
}
