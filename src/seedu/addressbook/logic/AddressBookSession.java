package seedu.addressbook.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;

/**
 * Represents the user's view of the address book.
 *
 * In particular, it implements the concept of a "last shown list", which is a list that contains a subset of
 * the address book's persons. This list can be filtered and sorted independently of the address book.
 */
public class AddressBookSession {
    private final AddressBook addressBook;
    private List<ReadOnlyPerson> lastShownList;

    public AddressBookSession(AddressBook addressBook) {
        this.addressBook = addressBook;
        this.lastShownList = new ArrayList<>();
    }

    public final AddressBook getAddressBook() {
        return addressBook;
    }

    public final List<ReadOnlyPerson> getLastShownList() {
        return Collections.unmodifiableList(lastShownList);
    }

    void setLastShownList(List<? extends ReadOnlyPerson> lastShownList) {
        // lastShownList may actually just be a view to this.lastShownList. To make sure there's no
        // clobbering of data, make a defensive copy.
        this.lastShownList = new ArrayList<>(lastShownList);
    }

    public void addPerson(Person toAdd) throws DuplicatePersonException {
        addressBook.addPerson(toAdd);
    }
}
