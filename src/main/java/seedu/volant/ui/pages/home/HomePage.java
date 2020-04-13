package seedu.volant.ui.pages.home;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class HomePage extends UiPart<Region> {
    private static final String FXML = "home/HomePage.fxml";
    private final Logger logger = LogsCenter.getLogger(HomePage.class);

    @FXML
    private ListView<Trip> tripListViewUpcoming;

    @FXML
    private ListView<Trip> tripListViewPast;

    public HomePage(ObservableList<Trip> tripList) {
        super(FXML);

        ObservableList<Trip> upcoming = FXCollections.observableArrayList();
        ObservableList<Trip> past = FXCollections.observableArrayList();

        for (Trip e : tripList) {
            if (e.getDateRange().getFrom().isAfter(LocalDate.now())) {
                upcoming.add(e);
            } else {
                past.add(e);
            }
        }


        if (upcoming.isEmpty()) {
            tripListViewUpcoming.setMaxHeight(0);
        } else {
            tripListViewUpcoming.setMinHeight(upcoming.size() * 105);
            tripListViewUpcoming.setMaxHeight(upcoming.size() * 105);
        }

        tripListViewUpcoming.setItems(upcoming);
        tripListViewUpcoming.setCellFactory(listViewUpcoming -> new TripListViewCellUpcoming());

        if (past.isEmpty()) {
            tripListViewPast.setMaxHeight(0);
        } else {
            tripListViewPast.setMinHeight(past.size() * 105);
            tripListViewPast.setMaxHeight(past.size() * 105);
        }

        tripListViewPast.setItems(past);
        tripListViewPast.setCellFactory(listViewPast -> new TripListViewCellPast(upcoming.size()));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Trip} using a {@code TripCard} for upcoming trips.
     */
    class TripListViewCellUpcoming extends ListCell<Trip> {
        @Override
        protected void updateItem(Trip trip, boolean empty) {
            super.updateItem(trip, empty);
            if (empty || trip == null) {
                setGraphic(null);
                setStyle("-fx-background-color: " + "#fff" + ";");
            } else {
                setGraphic(new HomePageTripCard(trip, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Trip} using a {@code TripCard} for past trips.
     */
    class TripListViewCellPast extends ListCell<Trip> {
        protected int upcoming;

        public TripListViewCellPast(int upcoming) {
            this.upcoming = upcoming;
        }

        @Override
        protected void updateItem(Trip trip, boolean empty) {
            super.updateItem(trip, empty);
            if (empty || trip == null) {
                setStyle("-fx-background-color: " + "#fff" + ";");
            } else {
                setGraphic(new HomePageTripCard(trip, getIndex() + 1 + upcoming).getRoot());
            }
        }
    }

}
