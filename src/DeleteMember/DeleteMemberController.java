package DeleteMember;

import Database.DatabaseHandler;
import DisplayMember.DisplayMemberController;
import Util.AssistantUtil;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class DeleteMemberController {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private StackPane rootPane;
    @FXML
    private TextField id;
    @FXML
    private JFXButton deleteButton;
    private DisplayMemberController display_member_controller;

    @FXML
    private void clickDeleteButton(ActionEvent event) {
        String memberId = id.getText();

        if (!DatabaseHandler.isValidBookAvailable(memberId)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Member ID.");
            return;
        }
        if (DatabaseHandler.deleteMember(memberId)) {
            display_member_controller.loadData();
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Success.");
        } else {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Failed. Member ID does not exist.");
        }
    }

    public void setUpDisplayMemberController(DisplayMemberController display_member_controller_) {
        display_member_controller = display_member_controller_;
    }
}
