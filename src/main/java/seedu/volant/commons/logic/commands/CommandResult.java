package seedu.volant.commons.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.volant.commons.logic.Page;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.trip.model.TripFeature;


/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    protected final String feedbackToUser;

    /** Help information should be shown to the user. */
    protected final boolean isShowHelp;

    /** The application should exit. */
    protected final boolean isExit;

    /** The application should switch pages. */
    protected boolean isGoto;

    protected boolean isBack;

    protected TripList tripList;

    protected Trip trip;

    protected TripFeature tripFeature;

    protected Page targetPage;

    protected boolean isHome;

    protected Model model;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean isGoto, boolean isBack) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = showHelp;
        this.isExit = exit;
        this.isGoto = isGoto;
        this.isBack = isBack;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false);
    }

    /**
     * Creates command result for commands to navigate:
     *      1) From a TRIP page back to the HOME page
     *      2) From a TRIP_FEATURE page back to the HOME page
     *      3) Back to HOME page from a TRIP page
     */
    public CommandResult(String feedbackToUser, TripList tripList) {
        this(feedbackToUser, false, false, true, false);
        this.tripList = tripList;
    }

    /**
     * Creates command result for commands to navigate:
     *      1) From the HOME page to a TRIP page
     *      2) Back to the TRIP page from a TRIP_FEATURE page
     */
    public CommandResult(String feedbackToUser, Trip trip) {
        this(feedbackToUser, false, false, true, false);
        this.trip = trip;
    }

    /**
     * Creates command result for commands to navigate:
     *      1) From a TRIP page to a TRIP_FEATURE page
     */
    public CommandResult(String feedbackToUser, TripFeature tripFeature) {
        this(feedbackToUser, false, false, true, false);
        this.tripFeature = tripFeature;
    }

    /**
     * Creates command result for commands to navigate to the Home page.
     */
    public CommandResult(String feedbackToUser, Model model) {
        this(feedbackToUser, false, false, false, false);
        this.isHome = true;
        this.model = model;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Page getTargetPage() {
        return targetPage;
    }

    public Model getModel() {
        return model;
    }

    public boolean isHome() {
        return isHome;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isGoto() {
        return isGoto;
    }

    public boolean isBack() {
        return isBack;
    }

    public void setBack() {
        this.isBack = true;
    }

    public TripList getTripList() {
        return tripList;
    }

    public Trip getTrip() {
        return trip;
    }

    public TripFeature getTripFeature() {
        return tripFeature;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit
                && isGoto == otherCommandResult.isGoto
                && isBack == otherCommandResult.isBack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isExit, isGoto);
    }

}
