package seedu.volant.home.model;

import static seedu.volant.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.model.UserPrefs;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setVolantFilePath(null));
    }

}
