package MainMenu;

import BookReturns.BookReturnsController;
import DisplayBook.DisplayBookController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
public class MainMenuController implements Initializable {

    @FXML
    private StackPane mainPartRoot;
    @FXML
    private Button memberViewButton;

    @FXML
    private Button bookViewButton;

    @FXML
    private Button addDeleteBookButton;

    @FXML
    private Button addDeleteMemberButton;

    @FXML
    private Button returnsBookButton;

    private StackPane bookReturnsLoader;

    private StackPane bookViewLoader;
    private StackPane addDeleteBookLoader;
    private StackPane memberViewLoader;
    private StackPane addDeleteMemberLoader;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader bookView = new FXMLLoader(getClass().getResource("/DisplayBook/DisplayBook.fxml"));
            bookViewLoader= bookView.load();
            DisplayBookController book_view_controller = bookView.getController();

            FXMLLoader bookReturns = new FXMLLoader(getClass().getResource("/BookReturns/BookReturns.fxml"));
            bookReturnsLoader = bookReturns.load();
            BookReturnsController book_return_controller = bookReturns.getController();
            book_return_controller.setUpBookViewController(book_view_controller);

            addDeleteBookLoader = new FXMLLoader(getClass().getResource("/AddDeleteBook/AddDeleteBook.fxml")).load();
            memberViewLoader = new FXMLLoader(getClass().getResource("/DisplayMember/DisplayMember.fxml")).load();
            addDeleteMemberLoader = new FXMLLoader(getClass().getResource("/AddDeleteMember/AddDeleteMember.fxml")).load();
            mainPartRoot.getChildren().setAll(Collections.singleton(bookReturnsLoader));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void clickReturnsBook(ActionEvent event) throws IOException {
        mainPartRoot.getChildren().setAll(Collections.singleton(bookReturnsLoader));
    }


    @FXML
    void clickBookView(ActionEvent event) throws IOException {

        mainPartRoot.getChildren().setAll(Collections.singleton(bookViewLoader));
    }

    @FXML
    void clickMemberView(ActionEvent event) throws IOException {
        mainPartRoot.getChildren().setAll(Collections.singleton(memberViewLoader));
    }

    @FXML
    void clickAddDeleteBook(ActionEvent event) {
        mainPartRoot.getChildren().setAll(Collections.singleton(addDeleteBookLoader));
    }

    @FXML
    void clickAddDeleteMember(ActionEvent event) {
        mainPartRoot.getChildren().setAll(Collections.singleton(addDeleteMemberLoader));
    }
}