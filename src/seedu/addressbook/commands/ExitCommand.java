package seedu.addressbook.commands;

import java.util.Stack;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Exits the program.\n\t"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Exiting Address Book as requested ...";
    
    public static boolean modifiesData = false;
    
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

	@Override
	public boolean modifiesData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCommandWord() {
		// TODO Auto-generated method stub
		return COMMAND_WORD;
	}

}
