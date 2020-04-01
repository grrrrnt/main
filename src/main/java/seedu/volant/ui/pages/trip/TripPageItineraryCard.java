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
 * An UI component that displays information of a {@code Itinerary} in
 * the form of a card on a trip page.
 */
public class TripPageItineraryCard extends UiPart<Region> {

    private static final String FXML = "trip/TripPageItineraryCard.fxml";

    private TripFeature tripFeature;

    @FXML
    private HBox itineraryPane;

    @FXML
    private Label itineraryTitle;

    @FXML
    private Label numActivities;

    @FXML
    private ImageView itineraryCardIcon;

    public TripPageItineraryCard(TripFeature feature) {
        super(FXML);

        Image itineraryCardImg = new Image("/images/itineraryIcon.png");
        itineraryCardIcon.setImage(itineraryCardImg);

        itineraryTitle.setText("Itinerary");
        numActivities.setText("You have " + feature.getNumItems() + " activities in you itinerary.");
    }

}
