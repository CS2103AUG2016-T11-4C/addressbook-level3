package seedu.addressbook.logic;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

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

    private static Person generatePersonWithName(String name) throws IllegalValueException {
        return new Person(
                new Name(name),
                new Phone("1", false),
                new Email("1@email", false),
                new Address("House of 1", false),
                new UniqueTagList(new Tag("tag"))
        );
    }

    @Test
    public void addPerson_callsAddPersonOnAddressBook() throws Exception {
        final Person toAdd = generatePersonWithName("Bob");
        final ReadOnlyPerson[] expected = { toAdd };
        addressBookSession.addPerson(toAdd);
        assertEquals(Arrays.asList(expected), addressBook.getAllPersons().immutableListView());
        assertEquals(Collections.emptyList(), addressBookSession.getLastShownList());
    }
}
