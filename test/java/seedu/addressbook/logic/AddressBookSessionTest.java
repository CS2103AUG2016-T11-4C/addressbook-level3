package seedu.addressbook.logic;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.AddressBook;

import static junit.framework.TestCase.assertEquals;

public class AddressBookSessionTest {
    private AddressBook addressBook;
    private AddressBookSession addressBookSession;

    @Before
    public void setup() {
        addressBook = new AddressBook();
        addressBookSession = new AddressBookSession(addressBook);
    }

    @Test
    public void constructor() {
        // addressBook is empty
        assertEquals(Collections.emptyList(), addressBook.getAllPersons().immutableListView());
        // lastShownList is empty
        assertEquals(Collections.emptyList(), addressBookSession.getLastShownList());
    }
}
