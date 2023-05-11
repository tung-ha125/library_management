package MainMenu;

import AddDeleteBook.AddDeleteBookController;
import AddDeleteMember.AddDeleteMemberController;
import BookReturns.BookReturnsController;
import DisplayBook.DisplayBookController;
import DisplayMember.DisplayMemberController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    @FXML
    private Button logoutButton;
    private StackPane bookReturnsLoader;
    private StackPane bookViewLoader;
    private StackPane addDeleteBookLoader;
    private StackPane memberViewLoader;
    private StackPane addDeleteMemberLoader;
    private DisplayBookController book_view_controller;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader bookView = new FXMLLoader(getClass().getResource("/DisplayBook/DisplayBook.fxml"));
            bookViewLoader = bookView.load();
            book_view_controller = bookView.getController();

            FXMLLoader bookReturns = new FXMLLoader(getClass().getResource("/BookReturns/BookReturns.fxml"));
            bookReturnsLoader = bookReturns.load();
            BookReturnsController book_return_controller = bookReturns.getController();
            book_return_controller.setUpBookViewController(book_view_controller);

            FXMLLoader addDeleteBook = new FXMLLoader(getClass().getResource("/AddDeleteBook/AddDeleteBook.fxml"));
            addDeleteBookLoader = addDeleteBook.load();
            AddDeleteBookController add_delete_book_controller = addDeleteBook.getController();
            add_delete_book_controller.setUpBookViewController(book_view_controller);

            FXMLLoader memberView = new FXMLLoader(getClass().getResource("/DisplayMember/DisplayMember.fxml"));
            memberViewLoader = memberView.load();
            DisplayMemberController memberViewController = memberView.getController();

            FXMLLoader addDeleteMember = new FXMLLoader(getClass().getResource("/AddDeleteMember/AddDeleteMember.fxml"));
            addDeleteMemberLoader = addDeleteMember.load();
            AddDeleteMemberController adddelete_member_controller = addDeleteMember.getController();
            adddelete_member_controller.setUpMemberViewController(memberViewController);

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
        book_view_controller.loadData();
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

    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();
        currentStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/LoginMenu/LoginMenu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Library Management");
        stage.setScene(scene);
        stage.show();
    }
}