package seedu.addressbook.commands;

public class EditCommand extends Command {
	 public static final String COMMAND_WORD = "edit";

	    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
	            + "Edits the person identified by the index number used in the last person listing.\n\t"
	            + "Parameters: INDEX\n\t prefix of field To Edit with updated details\n\t"
	            + "Example: " + COMMAND_WORD + " 1" + " p/81234567";

	    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Edited Person: %1$s";

	@Override
	public CommandResult execute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifiesData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCommandWord() {
		// TODO Auto-generated method stub
		return null;
	}

}
