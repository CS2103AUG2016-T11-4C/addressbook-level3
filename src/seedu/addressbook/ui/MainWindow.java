package seedu.addressbook.ui;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.List;
import java.util.Optional;

import static seedu.addressbook.common.Messages.*;

/**
 * Main Window of the GUI.
 */
public class MainWindow {

    private Logic logic;
    private Stoppable mainApp;

    public MainWindow(){
    }

    public void initialize() {
        personsIndexCol.setCellValueFactory(col -> new ReadOnlyObjectWrapper<ReadOnlyPerson>(col.getValue()));
        // Cell factory for displaying the row index
        personsIndexCol.setCellFactory(col -> {
            return new TableCell<ReadOnlyPerson, ReadOnlyPerson>() {
                @Override
                protected void updateItem(ReadOnlyPerson item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(Integer.toString(getTableRow().getIndex() + 1));
                    }
                }
            };
        });

        personsNameCol.setCellValueFactory(col -> new ReadOnlyObjectWrapper<Name>(col.getValue().getName()));

        personsPhoneCol.setCellValueFactory(col -> new ReadOnlyObjectWrapper<Phone>(col.getValue().getPhone()));
        personsPhoneCol.setCellFactory(col -> {
            return new TableCell<ReadOnlyPerson, Phone>() {
                @Override
                protected void updateItem(Phone item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else if (item.isPrivate()) {
                        setText("<private>");
                    } else {
                        setText(item.value);
                    }
                }
            };
        });

        personsEmailCol.setCellValueFactory(col -> new ReadOnlyObjectWrapper<Email>(col.getValue().getEmail()));
        personsEmailCol.setCellFactory(col -> {
            return new TableCell<ReadOnlyPerson, Email>() {
                @Override
                protected void updateItem(Email item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else if (item.isPrivate()) {
                        setText("<private>");
                    } else {
                        setText(item.value);
                    }
                }
            };
        });

        personsAddressCol.setCellValueFactory(col -> new ReadOnlyObjectWrapper<Address>(col.getValue().getAddress()));
        personsAddressCol.setCellFactory(col -> {
            return new TableCell<ReadOnlyPerson, Address>() {
                @Override
                protected void updateItem(Address item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else if (item.isPrivate()) {
                        setText("<private>");
                    } else {
                        setText(item.value);
                    }
                }
            };
        });

        personsTagsCol.setCellValueFactory(col -> new ReadOnlyObjectWrapper<UniqueTagList>(col.getValue().getTags()));
    }

    public void setLogic(Logic logic){
        this.logic = logic;
        personsView.setItems(logic.getAddressBookSession().getLastShownList());
    }

    public void setMainApp(Stoppable mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private TextArea outputConsole;

    @FXML
    private TextField commandInput;

    @FXML
    private TableView<ReadOnlyPerson> personsView;
    @FXML
    private TableColumn<ReadOnlyPerson, ReadOnlyPerson> personsIndexCol;
    @FXML
    private TableColumn<ReadOnlyPerson, Name> personsNameCol;
    @FXML
    private TableColumn<ReadOnlyPerson, Phone> personsPhoneCol;
    @FXML
    private TableColumn<ReadOnlyPerson, Email> personsEmailCol;
    @FXML
    private TableColumn<ReadOnlyPerson, Address> personsAddressCol;
    @FXML
    private TableColumn<ReadOnlyPerson, UniqueTagList> personsTagsCol;

    @FXML
    void onCommand(ActionEvent event) {
        try {
            String userCommandText = commandInput.getText();
            CommandResult result = logic.execute(userCommandText);
            if(isExitCommand(result)){
                exitApp();
                return;
            }
            displayResult(result);
            clearCommandInput();
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void exitApp() throws Exception {
        mainApp.stop();
    }

    /** Returns true of the result given is the result of an exit command */
    private boolean isExitCommand(CommandResult result) {
        return result.feedbackToUser.equals(ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    /** Clears the command input box */
    private void clearCommandInput() {
        commandInput.setText("");
    }

    /** Clears the output display area */
    public void clearOutputConsole(){
        outputConsole.clear();
    }

    /** Displays the result of a command execution to the user. */
    public void displayResult(CommandResult result) {
        clearOutputConsole();
        final Optional<List<? extends ReadOnlyPerson>> resultPersons = result.getRelevantPersons();
        if(resultPersons.isPresent()) {
            display(resultPersons.get());
        }
        display(result.feedbackToUser);
    }

    public void displayWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        display(MESSAGE_WELCOME, version, MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE, storageFileInfo);
    }

    /**
     * Displays the list of persons in the output display area, formatted as an indexed list.
     * Private contact details are hidden.
     */
    private void display(List<? extends ReadOnlyPerson> persons) {
        display(new Formatter().format(persons));
    }

    /**
     * Displays the given messages on the output display area, after formatting appropriately.
     */
    private void display(String... messages) {
        outputConsole.setText(outputConsole.getText() + new Formatter().format(messages));
    }

}
