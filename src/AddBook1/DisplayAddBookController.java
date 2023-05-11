package AddBook1;

import DisplayBook.DisplayBookController;
import Util.AssistantUtil;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import Database.DatabaseHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayAddBookController implements Initializable {
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private TextField author;
    @FXML
    private TextField publisher;
    @FXML
    private TextField id;
    @FXML
    private TextField title;
    @FXML
    private TextField intcode;
    @FXML
    private TextField available;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    private DisplayBookController display_book_controller;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void clickSaveButton(ActionEvent event) {
        try {
            String bookName = title.getText();
            String bookId = id.getText();
            String bookAuthor = author.getText();
            String bookPublisher = publisher.getText();
            String bookIntcode = intcode.getText();
            String boolAvailable = available.getText();

            if (bookId.isEmpty() || bookAuthor.isEmpty() || bookName.isEmpty()) {
                AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Please enter in all fields");
                return;
            }

            if (!DatabaseHandler.isValidBookId(bookId)) {
                AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Book ID.");
                return;
            } else if (!DatabaseHandler.isValidBookAvailable(boolAvailable)) {
                AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Book Available.");
                return;
            }

            if (DatabaseHandler.addBook(bookId, bookName, bookAuthor, bookPublisher, bookIntcode, boolAvailable)) {
                AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Success.");
                display_book_controller.loadData();
            } else {
                AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Failed.");
            }
        } catch (Exception e) {

        }
    }

    @FXML
    private void clickCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setUpDisplayBookController(DisplayBookController display_book_controller_) {
        display_book_controller = display_book_controller_;
    }

}
