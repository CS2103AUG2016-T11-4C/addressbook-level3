package seedu.addressbook.logic;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddressBookSessionTest {
    private AddressBook addressBook;
    private AddressBookSession addressBookSession;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

    @Test
    public void getPerson_invalidIndex_throwsIndexOutOfBoundsException() {
        thrown.expect(IndexOutOfBoundsException.class);
        addressBookSession.getPerson(1);
    }

    @Test
    public void getPerson_validIndex_returnsCorrectPerson() throws Exception {
        final Person bob = generatePersonWithName("Bob");
        final Person bill = generatePersonWithName("Bill");
        addressBook.addPerson(bob);
        addressBook.addPerson(bill);
        // Note that the lastShownList reverses the order of persons, to test that
        // addressBookSession uses the indexes of the lastShownList
        ReadOnlyPerson[] lastShownList = { bill, bob };
        addressBookSession.setLastShownList(Arrays.asList(lastShownList));
        assertEquals(bill, addressBookSession.getPerson(1));
        assertEquals(bob, addressBookSession.getPerson(2));
    }

    @Test
    public void removePerson_invalidIndex_throwsIndexOutOfBoundsException() throws Exception {
        thrown.expect(IndexOutOfBoundsException.class);
        addressBookSession.removePerson(1);
    }

    @Test
    public void removePerson_validIndex_removesCorrectPerson() throws Exception {
        final Person bob = generatePersonWithName("Bob");
        final Person bill = generatePersonWithName("Bill");
        addressBook.addPerson(bob);
        addressBook.addPerson(bill);
        // Note that the lastShownList reverses the order of persons, to test that
        // addressBookSession uses the indexes of the lastShownList
        ReadOnlyPerson[] lastShownList = { bill, bob };
        addressBookSession.setLastShownList(Arrays.asList(lastShownList));
        addressBookSession.removePerson(2);
        final ReadOnlyPerson[] expectedAB = { bill };
        assertEquals(Arrays.asList(expectedAB), addressBook.getAllPersons().immutableListView());
        // lastShownList is untouched
        assertEquals(Arrays.asList(lastShownList), addressBookSession.getLastShownList());
    }

    @Test
    public void containsPerson_doesContainsPerson_returnsTrue() throws Exception {
        final Person bob = generatePersonWithName("Bob");
        addressBook.addPerson(bob);
        assertTrue(addressBookSession.containsPerson(bob));
    }

    @Test
    public void containsPerson_doesNotContainsPerson_returnsFalse() throws Exception {
        final Person bob = generatePersonWithName("Bob");
        assertFalse(addressBookSession.containsPerson(bob));
    }

    @Test
    public void clear_clearsAllPersons() throws Exception {
        final Person bob = generatePersonWithName("Bob");
        final ReadOnlyPerson[] lastShownList = { bob };
        addressBook.addPerson(bob);
        addressBookSession.setLastShownList(Arrays.asList(lastShownList));
        addressBookSession.clear();
        assertEquals(Collections.emptyList(), addressBook.getAllPersons().immutableListView());
        // lastShownList is also cleared
        assertEquals(Collections.emptyList(), addressBookSession.getLastShownList());
    }

    @Test
    public void setFilter_withPredicate_filtersLastShownList() throws Exception {
        final Person bob = generatePersonWithName("Bob");
        final Person bill = generatePersonWithName("Bill");
        addressBook.addPerson(bob);
        addressBook.addPerson(bill);
        addressBookSession.setFilter(person -> person.getName().fullName.equals("Bob"));
        ReadOnlyPerson[] expected = { bob };
        assertEquals(Arrays.asList(expected), addressBookSession.getLastShownList());
    }

    @Test
    public void setFilter_null_showsAll() throws Exception {
        final Person bob = generatePersonWithName("Bob");
        final Person bill = generatePersonWithName("Bill");
        addressBook.addPerson(bob);
        addressBook.addPerson(bill);
        addressBookSession.setFilter(null);
        ReadOnlyPerson[] expected = { bob, bill };
        assertEquals(Arrays.asList(expected), addressBookSession.getLastShownList());
    }
}
