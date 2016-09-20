package seedu.addressbook.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

import static seedu.addressbook.ui.Gui.DISPLAYED_INDEX_OFFSET;

/**
 * Represents the user's view of the address book.
 *
 * In particular, it implements the concept of a "last shown list", which is a list that contains a subset of
 * the address book's persons. This list can be filtered and sorted independently of the address book.
 */
public class AddressBookSession {
    private final AddressBook addressBook;
    private List<ReadOnlyPerson> lastShownList;
    private Predicate<ReadOnlyPerson> filter;

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

    public final Predicate<ReadOnlyPerson> getFilter() {
        return filter;
    }

    public void setFilter(Predicate<ReadOnlyPerson> filter) {
        this.filter = filter;
        lastShownList.clear();
        for (Person person : addressBook.getAllPersons()) {
            if (filter == null || filter.test(person)) {
                lastShownList.add(person);
            }
        }
    }

    public void addPerson(Person toAdd) throws DuplicatePersonException {
        addressBook.addPerson(toAdd);
    }

    public ReadOnlyPerson getPerson(int index) throws IndexOutOfBoundsException {
        return lastShownList.get(index - DISPLAYED_INDEX_OFFSET);
    }

    public void removePerson(int index) throws IndexOutOfBoundsException, PersonNotFoundException {
        final ReadOnlyPerson target = getPerson(index);
        addressBook.removePerson(target);
    }

    public boolean containsPerson(ReadOnlyPerson key) {
        return addressBook.containsPerson(key);
    }

    public void clear() {
        addressBook.clear();
    }
}
