package seedu.volant.itinerary.model.activity;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.util.AppUtil.checkArgument;

/**
 * Represents an Activity's title in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle (String)}
 */
public class Title {

    public static final String MESSAGE_CONSTRAINTS = "Title should not be blank :(";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String title;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Title(String name) {
        requireNonNull(name);
        checkArgument(isValidTitle(name), MESSAGE_CONSTRAINTS);
        title = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && title.equals(((Title) other).title)); // state check
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

}
