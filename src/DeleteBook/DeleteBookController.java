package DeleteBook;

import java.net.URL;
import java.util.ResourceBundle;
import Database.DatabaseHandler;
import DisplayBook.DisplayBookController;
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
    private DisplayBookController display_book_controller;

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
            display_book_controller.loadData();
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Success.");
        } else {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Failed. Book ID does not exist.");
        }
    }

    public void setUpDisplayBookController(DisplayBookController display_book_controller_) {
        display_book_controller = display_book_controller_;
    }
}