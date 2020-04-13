package seedu.volant.journal.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.volant.testutil.TypicalEntries;

public class EntryListTest {
    private final EntryList entryList = new EntryList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), entryList.getEntryList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> entryList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEntryList_replacesData() {
        EntryList newData = TypicalEntries.getTypicalEntryList();
        entryList.resetData(newData);
        assertEquals(newData, entryList);
    }

    @Test
    public void hasEntry_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> entryList.hasEntry(null));
    }

    @Test
    public void hasEntry_entryNotInEntryList_returnsFalse() {
        assertFalse(entryList.hasEntry(TypicalEntries.A));
    }

    @Test
    public void hasEntry_entryInEntryList_returnsTrue() {
        entryList.addEntry(TypicalEntries.A);
        assertTrue(entryList.hasEntry(TypicalEntries.A));
    }

    @Test
    public void getEntryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> entryList.getEntryList().remove(0));
    }
}
