package seedu.volant.journal.model;

/**
 * Represents a state of Feeling in a journal Entry.
 */
public enum Feeling {
    ANGRY, CONFUSED, EXCITED, HAPPY, SAD, SCARED, TIRED, WORRIED, NULL;
    public static final String MESSAGE_CONSTRAINTS = "I feel like that FEELING... is invalid \n"
            + "Here are the available FEELING parameters: \n"
            + "TIRED, HAPPY, SAD, EXCITED, ANGRY, WORRIED, SCARED, CONFUSED";
}
