package seedu.volant.itinerary.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.volant.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.commons.util.JsonUtil;
import seedu.volant.itinerary.model.ActivityList;
import seedu.volant.testutil.TypicalActivities;

class JsonSerializableActivityListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableActivityListTest");
    private static final Path TYPICAL_ACTIVITIES_FILE = TEST_DATA_FOLDER.resolve("typicalActivitiesActivityList.json");
    private static final Path INVALID_ACTIVITY_FILE = TEST_DATA_FOLDER.resolve("invalidActivityActivityList.json");
    private static final Path DUPLICATE_ACTIVITY_FILE = TEST_DATA_FOLDER.resolve("duplicateActivityActivityList.json");

    @Test
    public void toModelType_typicalActivitiesFile_success() throws Exception {
        JsonSerializableActivityList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ACTIVITIES_FILE,
                JsonSerializableActivityList.class).get();
        ActivityList activityListFromFile = dataFromFile.toModelType();
        ActivityList typicalPersonsActivityList = TypicalActivities.getTypicalActivityList();
        assertEquals(activityListFromFile, typicalPersonsActivityList);
    }

    @Test
    public void toModelType_invalidActivityFile_throwsIllegalValueException() throws Exception {
        JsonSerializableActivityList dataFromFile = JsonUtil.readJsonFile(INVALID_ACTIVITY_FILE,
                JsonSerializableActivityList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateActivity_throwsIllegalValueException() throws Exception {
        JsonSerializableActivityList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ACTIVITY_FILE,
                JsonSerializableActivityList.class).get();
        assertThrows(IllegalValueException.class,
                JsonSerializableActivityList.MESSAGE_DUPLICATE_ACTIVITY, dataFromFile::toModelType);
    }
}
