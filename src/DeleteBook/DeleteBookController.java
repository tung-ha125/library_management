package DeleteBook;

import java.net.URL;
import java.util.ResourceBundle;
import Database.DatabaseHandler;
import Util.AssistantUtil;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DeleteBookController implements Initializable {


    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private StackPane rootPane;
    @FXML
    private TextField id;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton cancelButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void clickDeleteButton(ActionEvent event) {
        String bookId = id.getText();

        if (!DatabaseHandler.isValidBookId(bookId)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Book ID.");
            return;
        }
        if (DatabaseHandler.deleteBook(bookId)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Success.");
        } else {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Failed. Book ID does not exist.");
        }
    }
}