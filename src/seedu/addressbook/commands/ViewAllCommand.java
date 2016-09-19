package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;

import static seedu.addressbook.ui.Gui.DISPLAYED_INDEX_OFFSET;

/**
 * Shows all details of the person identified using the last displayed index.
 * Private contact details are shown.
 */
public class ViewAllCommand extends Command {

    public static final String COMMAND_WORD = "viewall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Shows all details of the person "
            + "identified by the index number in the last shown person listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_DETAILS = "Viewing person: %1$s";

    private final int targetIndex;

    public ViewAllCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = relevantPersons.get(targetIndex - DISPLAYED_INDEX_OFFSET);
            if (!addressBook.containsPerson(target)) {
                return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
            }
            return new CommandResult(String.format(MESSAGE_VIEW_PERSON_DETAILS, target.getAsTextShowAll()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    public int getTargetIndex() {
        return targetIndex;
    }
}
