package LoginMenu;

import Database.DatabaseHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginMenuController {
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private AnchorPane main_form;
    @FXML
    private TextField username;
    @FXML
    private Text warningText;

    @FXML
    void loginAdmin(ActionEvent event) throws SQLException, IOException {
        Connection conn = DatabaseHandler.connectToDatabase();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM adminaccount WHERE username = ? AND password = ?");
        stmt.setString(1, username.getText());
        stmt.setString(2, password.getText());

        ResultSet rs = stmt.executeQuery();
        warningText.setVisible(false);
        if (!rs.next()) {
            setWarningText("Account is not found.");
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Library Management");
            stage.setScene(scene);
            Stage currentStage = (Stage) loginBtn.getScene().getWindow();
            currentStage.close();
            stage.show();
        }
    }

    private void setWarningText(String title) {
        warningText.setText(title);
        if (!warningText.isVisible()) {
            warningText.setVisible(true);
        }
    }
}
