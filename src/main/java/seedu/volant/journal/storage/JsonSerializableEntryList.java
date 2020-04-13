package seedu.volant.journal.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.EntryList;
import seedu.volant.journal.model.ReadOnlyEntryList;

/**
 * An Immutable EntryList that is serializable to JSON format.
 */
@JsonRootName(value = "volant")
public class JsonSerializableEntryList {
    public static final String MESSAGE_DUPLICATE_PERSON = "Trip list contains duplicate trip(s).";

    private final List<JsonAdaptedEntry> entries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEntryList} with the given entries.
     */
    @JsonCreator
    public JsonSerializableEntryList(@JsonProperty("entries") List<JsonAdaptedEntry> entries) {
        this.entries.addAll(entries);
    }

    /**
     * Converts a given {@code ReadOnlyEntryList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEntryList}.
     */
    public JsonSerializableEntryList(ReadOnlyEntryList source) {
        entries.addAll(source.getEntryList().stream().map(JsonAdaptedEntry::new).collect(Collectors.toList()));
    }

    /**
     * Converts this entry list into the model's {@code EntryList} object.
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EntryList toModelType() throws IllegalValueException {
        EntryList entryList = new EntryList();
        for (JsonAdaptedEntry jsonAdaptedEntry : entries) {
            Entry entry = jsonAdaptedEntry.toModelType();
            if (entryList.hasEntry(entry)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            entryList.addEntry(entry);
        }
        return entryList;
    }

}
