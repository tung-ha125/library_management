package AddDeleteBook;

import AddBook1.DisplayAddBookController;
import DeleteBook.DeleteBookController;
import DisplayBook.DisplayBookController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class AddDeleteBookController implements Initializable {

    @FXML
    private StackPane addBookRoot;
    @FXML
    private StackPane deleteBookRoot;
    private DisplayBookController display_book_controller;
    private DisplayAddBookController add_book_controller;
    private DeleteBookController delete_book_controller;
    private FXMLLoader addBookLoader;
    private FXMLLoader deleteBookLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBookLoader = new FXMLLoader(getClass().getResource("/AddBook1/AddBook1.fxml"));
        deleteBookLoader = new FXMLLoader(getClass().getResource("/DeleteBook/DeleteBook.fxml"));
        try {
            addBookRoot.getChildren().setAll(Collections.singleton(addBookLoader.load()));
            deleteBookRoot.getChildren().setAll(Collections.singleton(deleteBookLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUpBookViewController(DisplayBookController controller) {
        display_book_controller = controller;
        add_book_controller = addBookLoader.getController();
        delete_book_controller = deleteBookLoader.getController();
        add_book_controller.setUpDisplayBookController(display_book_controller);
        delete_book_controller.setUpDisplayBookController(display_book_controller);
    }
}
