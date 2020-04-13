package seedu.volant.journal.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_FEELING;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_WEATHER;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.util.CollectionUtil;
import seedu.volant.home.model.trip.Location;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.Feeling;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.journal.model.Weather;

/**
 * Edits an existing entry in the EntryList.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an entry in the journal. \n"
            + "Parameters: \n"
            + "INDEX "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "[" + PREFIX_TEXT + "TEXT] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_FEELING + "FEELING] "
            + "[" + PREFIX_WEATHER + "WEATHER] \n"

            + "Example:\n"
            + COMMAND_WORD + " 1 "
            + PREFIX_TEXT + "I love watermelon "
            + PREFIX_LOCATION + "Watermelon Centre "

            + "\nNOTE:\n"
            + "+ INDEX must be a positive integer within range of the number of entires in the journal.\n"
            + "+ At least one of the parameters must be provided.";

    public static final String MESSAGE_EDIT_ENTRY_SUCCESS = "Entry edited: %1$s";
    public static final String MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX = "The entry index provided is invalid.";

    private final Index targetIndex;
    private final EditEntryDescriptor editEntryDescriptor;

    /**
     * Creates an AddCommand to add the specified {@code Entry}
     */
    public EditCommand(Index targetIndex, EditEntryDescriptor editEntryDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editEntryDescriptor);

        this.targetIndex = targetIndex;
        this.editEntryDescriptor = editEntryDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        JournalModelManager journalModel = ((JournalModelManager) model);
        List<Entry> lastShownList = journalModel.getFilteredEntryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        Entry entryToEdit = lastShownList.get(targetIndex.getZeroBased());
        Entry editedEntry = createEditedEntry(entryToEdit, editEntryDescriptor);
        journalModel.editEntry(entryToEdit, editedEntry);
        return new CommandResult(String.format(MESSAGE_EDIT_ENTRY_SUCCESS, entryToEdit));
    }

    /**
     * Creates and returns a {@code Trip} with the details of {@code tripToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Entry createEditedEntry(Entry entryToEdit, EditEntryDescriptor editEntryDescriptor) {
        assert entryToEdit != null;

        String updatedText = editEntryDescriptor.getText().orElse(entryToEdit.getText());
        Location updatedLocation = editEntryDescriptor.getLocation().orElse(entryToEdit.getLocation());
        LocalDate updatedDate = editEntryDescriptor.getDate().orElse(entryToEdit.getDate());
        LocalTime updatedTime = editEntryDescriptor.getTime().orElse(entryToEdit.getTime());
        Feeling updatedFeeling = editEntryDescriptor.getFeeling().orElse(entryToEdit.getFeeling());
        Weather updatedWeather = editEntryDescriptor.getWeather().orElse(entryToEdit.getWeather());

        return new Entry(updatedDate, updatedTime, updatedText, updatedFeeling,
                updatedWeather, updatedLocation.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && targetIndex.equals(((EditCommand) other).targetIndex)); // state check
    }

    /**
     * Stores the details to edit the trip with. Each non-empty field value will replace the
     * corresponding field value of the trip.
     */
    public static class EditEntryDescriptor {
        private String text;
        private LocalDate date;
        private LocalTime time;
        private Feeling feeling;
        private Weather weather;
        private Location location;

        public EditEntryDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditEntryDescriptor(EditEntryDescriptor toCopy) {
            setText(toCopy.text);
            setLocation(toCopy.location);
            setDate(toCopy.date);
            setTime(toCopy.time);
            setFeeling(toCopy.feeling);
            setWeather(toCopy.weather);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(text, date, time, location, feeling, weather);
        }

        public void setText(String text) {
            this.text = text;
        }

        public Optional<String> getText() {
            return Optional.ofNullable(text);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }

        public Optional<LocalTime> getTime() {
            return Optional.ofNullable(time);
        }

        public void setFeeling(Feeling feeling) {
            this.feeling = feeling;
        }

        public Optional<Feeling> getFeeling() {
            return Optional.ofNullable(feeling);
        }

        public void setWeather(Weather weather) {
            this.weather = weather;
        }

        public Optional<Weather> getWeather() {
            return Optional.ofNullable(weather);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEntryDescriptor)) {
                return false;
            }

            // state check
            EditEntryDescriptor e = (EditEntryDescriptor) other;

            return getText().equals(e.getText())
                    && getLocation().equals(e.getLocation())
                    && getDate().equals(e.getDate())
                    && getTime().equals(e.getTime())
                    && getFeeling().equals(e.getTime())
                    && getWeather().equals(e.getWeather());
        }

    }
}

