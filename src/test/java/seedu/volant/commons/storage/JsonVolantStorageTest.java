package seedu.volant.commons.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.volant.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.model.TripList;

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
