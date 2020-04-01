package seedu.volant.journal.model.entry;

/**
 * Represents a state of Weather in a journal Entry.
 */
public enum Weather {
    NULL, SUNNY, RAINY, SNOWY, CLOUDY, COLD, DARK, HOT, COOL;
    public static final String MESSAGE_CONSTRAINTS = "Global warming hasn't gotten THAT bad yet... \n"
            + "Here are the available WEATHER parameters: \n"
            + "SUNNY, RAINY, SNOWY, CLOUDY, COLD, DARK, HOT, COOL";
}
