package seedu.addressbook.commands;

import java.text.Collator;
import java.util.Collections;
import java.util.List;

import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

public class Sort extends Command {
	public static final String COMMAND_WORD = "sort entries";
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Displays all the entries in alphabetical order.\n\t";
	public static final String MESSAGE_SUCCESS = "Sorted List in alphabetical order";

	public Sort(ReadOnlyPerson allPersons){
		super();
	}

	@Override
	  public CommandResult execute() {
			List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
	       // List<Name> allPersonsNames = addressBook.getAllPersons().immutableListView();
			List<Name> allPersonsNames = (List<Name>) ((Person) allPersons).getName();
	        Collections.sort(allPersonsNames, Collator.getInstance());
	        //return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
	        return new CommandResult(MESSAGE_SUCCESS, allPersons);
	    }
}
