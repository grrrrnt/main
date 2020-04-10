package seedu.volant.commons.storage;

/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.volant.testutil.Assert.assertThrows;
*/

import java.nio.file.Path;
import java.nio.file.Paths;

/*import org.junit.jupiter.api.Test;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.commons.util.JsonUtil;
import seedu.volant.home.model.TripList;
import seedu.volant.home.storage.JsonSerializableTripList;
import seedu.volant.testutil.TypicalTrips;*/

public class JsonSerializableLocationBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableLocationBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    //    @Test
    //    public void toModelType_typicalPersonsFile_success() throws Exception {
    //        JsonSerializableTripList dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
    //                JsonSerializableTripList.class).get();
    //        TripList addressBookFromFile = dataFromFile.toModelType();
    //        TripList typicalPersonsAddressBook = TypicalTrips.getTypicalTripList();
    //        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    //    }
    //
    //    @Test
    //    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
    //        JsonSerializableTripList dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
    //                JsonSerializableTripList.class).get();
    //        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
    //        JsonSerializableTripList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
    //                JsonSerializableTripList.class).get();
    //        assertThrows(IllegalValueException.class, JsonSerializableTripList.MESSAGE_DUPLICATE_TRIP,
    //                dataFromFile::toModelType);
    //    }

}
