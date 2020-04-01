package seedu.volant.journal.model.entry;

/**
 * Represents a state of Feeling in a journal Entry.
 */
public enum Feeling {
    NULL, TIRED, HAPPY, SAD, EXCITED, WORRIED, SCARED, ANGRY, CONFUSED;
    public static final String MESSAGE_CONSTRAINTS = "I feel like that FEELING... is invalid \n"
            + "Here are the available FEELING parameters: \n"
            + "TIRED, HAPPY, SAD, EXCITED, ANGRY, WORRIED, SCARED, CONFUSED";
}
