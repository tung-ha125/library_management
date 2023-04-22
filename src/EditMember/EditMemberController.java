package EditMember;

import Database.DatabaseHandler;
import DisplayMember.RenderedMember;
import Util.AssistantUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EditMemberController {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private StackPane rootPane;
    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField mobileField;
    @FXML
    private TextField emailField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    private RenderedMember member;
    private boolean saveClicked = false;
    private Stage dialogStage;
    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMember(RenderedMember member) {
        this.member = member;
        nameField.setText(member.getName());
        idField.setText(member.getMemberId());
        mobileField.setText(member.getMobile());
        emailField.setText(member.getEmail());
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSave() {
        String memberName = nameField.getText();
        String memberMobile = mobileField.getText();
        String memberEmail = emailField.getText();

        if (!DatabaseHandler.isValidName(memberName)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Member Name.");
            return;
        } else if (!DatabaseHandler.isValidEmail(memberEmail)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Member Email.");
            return;
        } else if (!DatabaseHandler.isValidPhoneNumber(memberMobile)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Member Phone Number.");
            return;
        }

        member.setName(memberName);
        member.setMobile(memberMobile);
        member.setEmail(memberEmail);

        //Put Rendered Member to Database
        Boolean flag = DatabaseHandler.editMember(member);
        if (flag) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, dialogStage, "Success.");
        }
        else {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Failed.");
        }
    }
}
