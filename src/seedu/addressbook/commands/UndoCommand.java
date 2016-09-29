package seedu.addressbook.commands;

import java.util.EmptyStackException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.logic.Logic;

public class UndoCommand extends Command{
	 
	 public static boolean modifiesData=false;
	
	 public static final String COMMAND_WORD = "undo";
	 
	 public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Undos previous action that modifies address book information.\n\t"
	            + "Example: " + COMMAND_WORD;

	@Override
	public CommandResult execute() {
		try{
		AddressBook prevState=Logic.stateStack.pop();
		addressBook.reset(prevState.getAllPersons(), prevState.getAllTags());
		Command undoneAction=Logic.modifyingDataCommandHistory.pop();
		return new CommandResult("Successfully undid previous "+undoneAction.getCommandWord());
		
		} catch (EmptyStackException e){
			return new CommandResult("No actions to undo.");
		}
		
				
	}

	@Override
	public boolean modifiesData() {
		
		return false;
	}

	@Override
	public String getCommandWord() {
		return COMMAND_WORD;
	}

}
