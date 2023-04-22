package AddMember;

import java.net.URL;
import java.util.ResourceBundle;

import Database.DatabaseHandler;
import Util.AssistantUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void clickSaveButton(ActionEvent event) {
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

}