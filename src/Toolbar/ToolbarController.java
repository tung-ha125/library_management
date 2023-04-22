package Toolbar;

import Util.AssistantUtil;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ToolbarController {

    @FXML
    private JFXButton deleteBookButton;
    @FXML
    private JFXButton addBookButton;
    @FXML
    private JFXButton addMemberButton;
    @FXML
    private JFXButton deleteMemberButton;

    @FXML
    void clickaddMemberButton(ActionEvent event) {
        AssistantUtil.loadWindow(getClass().getResource("/AddMember/AddMember.fxml"), "Add Member", null);
    }

    @FXML
    void clickDeleteMemberButton(ActionEvent event) {
        AssistantUtil.loadWindow(getClass().getResource("/DeleteMember/DeleteMember.fxml"), "Delete Member", null);
    }

    @FXML
    void clickaddBookButton(ActionEvent event) {
        AssistantUtil.loadWindow(getClass().getResource("/addBook/DisplayAddBook.fxml"), "Add Book", null);
    }

    @FXML
    void clickDeleteBookButton(ActionEvent event) {
        AssistantUtil.loadWindow(getClass().getResource("/DeleteBook/DeleteBook.fxml"), "Delete Book", null);
    }
}
