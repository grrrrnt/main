package seedu.volant.journal.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.journal.model.Entry;
import seedu.volant.trip.model.Journal;

/**
 * Jackson-friendly version of {@link Journal}.
 */
public class JsonAdaptedJournal {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Journal's %s field is missing!";

    private final String title;
    private final List<JsonAdaptedEntry> entryList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedJournal} with the given title and entry list.
     * @param title
     * @param entryList
     */
    @JsonCreator
    public JsonAdaptedJournal(@JsonProperty("title") String title,
                                @JsonProperty("entryList") List<JsonAdaptedEntry> entryList) {
        this.title = title;
        if (entryList != null) {
            this.entryList.addAll(entryList);
        }
    }

    /**
     * Converts a given {@code Journal} into this class for Jackson use.
     */
    public Journal toModelType() throws IllegalValueException {
        final List<Entry> entries = new ArrayList<>();

        for (JsonAdaptedEntry entry : entryList) {
            entries.add(entry.toModelType());
        }

        final String modelTitle = title;

        //final Set<Entry> modelEntries = new HashSet<>(entries);

        return new Journal(modelTitle);
    }

}
