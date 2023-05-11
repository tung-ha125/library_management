package AddMember;

import java.net.URL;
import java.util.ResourceBundle;

import Database.DatabaseHandler;
import DisplayMember.DisplayMemberController;
import Util.AssistantUtil;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddMemberController implements Initializable {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private StackPane rootPane;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    private DisplayMemberController display_member_controller;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickSaveButton(ActionEvent event) {
        String memberName = name.getText();
        String memberEmail = email.getText();
        String memberPhone = phone.getText();

        //Check valid
        if ("".equals(memberName) || "".equals(memberEmail) || "".equals(memberPhone)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Please enter in all fields.");
            return;
        }
        if (!DatabaseHandler.isValidName(memberName)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Member Name.");
            return;
        } else if (!DatabaseHandler.isValidEmail(memberEmail)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Member Email.");
            return;
        } else if (!DatabaseHandler.isValidPhoneNumber(memberPhone)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Member Phone Number.");
            return;
        }

        //Save
        if (DatabaseHandler.pushNewMember(memberName, memberPhone, memberEmail)) {
            display_member_controller.loadData();
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Add success.");
        } else {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Add failed.");
        }
    }

    @FXML
    private void clickCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setUpDisplayMemberController(DisplayMemberController display_member_controller_) {
        display_member_controller = display_member_controller_;
    }
}