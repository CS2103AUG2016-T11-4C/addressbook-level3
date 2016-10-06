package seedu.addressbook.commands;

import java.util.Collections;
import java.util.List;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

public class Sort extends Command {
	public static final String COMMAND_WORD = "sort entries";
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all the entries in alphabetical order.\n\t";
	public static final String MESSAGE_SUCCESS = "Sorted List: %1$s";
	
	
	 
	 @Override
	  public CommandResult execute() {
	        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
	        Collections.sort((List<ReadOnlyPerson>) allPersons);
	        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
	    }
		
}
