package seedu.volant.home.model.trip;

import static seedu.volant.commons.util.StringUtil.formatDate;

import java.time.LocalDate;

/**
 * Represents date range of a trip.
 */
public class DateRange {

    public static final String MESSAGE_CONSTRAINTS = "Please enter date range in \"DD-MM-YYYY to DD-MM-YYYY\" format!!";
    public static final String VALIDATION_REGEX = "^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]"
            + "([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})( to )([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]"
            + "([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$";

    public final String value;

    protected LocalDate from;
    protected LocalDate to;

    public DateRange(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
        value = String.format("%02d", from.getDayOfMonth()) + "-"
                + String.format("%02d", from.getMonthValue()) + "-"
                + from.getYear() + " to "
                + String.format("%02d", to.getDayOfMonth()) + "-"
                + String.format("%02d", to.getMonthValue()) + "-"
                + to.getYear();

    }

    public static boolean isValidDateRange(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return formatDate(from) + " - " + formatDate(to);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DateRange)
                && other.toString().equals(this.toString()); // state check
    }

}
