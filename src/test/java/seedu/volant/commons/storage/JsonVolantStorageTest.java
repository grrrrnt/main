package seedu.volant.commons.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.volant.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.model.TripList;

//import static seedu.volant.testutil.TypicalTrips.A;
//import static seedu.volant.testutil.TypicalTrips.B;
//import static seedu.volant.testutil.TypicalTrips.C;
//import static seedu.volant.testutil.TypicalTrips.getTypicalTripList;
//import seedu.volant.commons.exceptions.DataConversionException;

public class JsonVolantStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonVolantStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTripList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTripList(null));
    }

    private java.util.Optional<ReadOnlyTripList> readTripList(String filePath) throws Exception {
        return new JsonVolantStorage(Paths.get(filePath)).readTripList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTripList("NonExistentFile.json").isPresent());
    }

    //    @Test
    //    public void read_notJsonFormat_exceptionThrown() {
    //        assertThrows(DataConversionException.class, () -> readTripList("notJsonFormatTripList.json"));
    //    }
    //
    //    @Test
    //    public void readTripList_invalidPersonTripList_throwDataConversionException() {
    //        assertThrows(DataConversionException.class, () -> readTripList("invalidPersonTripList.json"));
    //    }
    //
    //    @Test
    //    public void readTripList_invalidAndValidPersonTripList_throwDataConversionException() {
    //        assertThrows(DataConversionException.class, () -> readTripList("invalidAndValidPersonTripList.json"));
    //    }
    //
    //    @Test
    //    public void readAndSaveTripList_allInOrder_success() throws Exception {
    //        Path filePath = testFolder.resolve("TempTripList.json");
    //        TripList original = getTypicalTripList();
    //        JsonVolantStorage jsonTripListStorage = new JsonVolantStorage(filePath);
    //
    //        // Save in new file and read back
    //        jsonTripListStorage.saveTripList(original, filePath);
    //        ReadOnlyTripList readBack = jsonTripListStorage.readTripList(filePath).get();
    //        assertEquals(original, new TripList(readBack));
    //
    //        // Modify data, overwrite exiting file, and read back
    //        original.addTrip(B);
    //        original.removeTrip(A);
    //        jsonTripListStorage.saveTripList(original, filePath);
    //        readBack = jsonTripListStorage.readTripList(filePath).get();
    //        assertEquals(original, new TripList(readBack));
    //
    //        // Save and read without specifying file path
    //        original.addTrip(C);
    //        jsonTripListStorage.saveTripList(original); // file path not specified
    //        readBack = jsonTripListStorage.readTripList().get(); // file path not specified
    //        assertEquals(original, new TripList(readBack));
    //
    //    }

    @Test
    public void saveTripList_nullTripList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTripList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code TripList} at the specified {@code filePath}.
     */
    private void saveTripList(ReadOnlyTripList tripList, String filePath) {
        try {
            new JsonVolantStorage(Paths.get(filePath))
                    .saveTripList(tripList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTripList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTripList(new TripList(), null));
    }
}
