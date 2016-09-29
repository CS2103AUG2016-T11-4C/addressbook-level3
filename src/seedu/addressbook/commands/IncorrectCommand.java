package seedu.addressbook.commands;


/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command{

    public final String feedbackToUser;
    public static boolean modifiesData = false;

    public IncorrectCommand(String feedbackToUser){
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
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
