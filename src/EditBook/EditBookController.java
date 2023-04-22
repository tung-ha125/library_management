package EditBook;

import Database.DatabaseHandler;
import DisplayBook.RenderedBook;
import Util.AssistantUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EditBookController {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private StackPane rootPane;
    @FXML
    private TextField idField;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField authorField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField intcodeField;
    @FXML
    private TextField publisherField;
    @FXML
    private Button saveButton;
    @FXML
    private TextField availableField;
    private RenderedBook book;
    private boolean saveClicked = false;
    private Stage dialogStage;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setBook(RenderedBook book) {
        this.book = book;
        idField.setText(book.getBookId());
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        publisherField.setText(book.getPublisher());
        intcodeField.setText(book.getIntCode());
        availableField.setText(String.valueOf(book.getAvailable()));
    }

    public RenderedBook getBook() {
        return this.book;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSave() {
        String bookName = titleField.getText();
        String bookId = idField.getText();
        String bookAuthor = authorField.getText();
        String bookPublisher = publisherField.getText();
        String bookIntcode = intcodeField.getText();
        String boolAvailable = availableField.getText();

        if (!DatabaseHandler.isValidBookAvailable(boolAvailable)) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Invalid Book Available.");
            return;
        }
        book.setBookId(bookId);
        book.setTitle(bookName);
        book.setAuthor(bookAuthor);
        book.setPublisher(bookPublisher);
        book.setIntCode(bookIntcode);
        book.setAvailable(Integer.parseInt(boolAvailable));

        //Put Rendered Member to Database
        Boolean flag = DatabaseHandler.editBook(book);
        if (flag) {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, dialogStage, "Success.");
        } else {
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Failed.");
        }
    }
}
