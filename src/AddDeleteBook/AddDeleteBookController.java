package AddDeleteBook;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader addBookLoader = new FXMLLoader(getClass().getResource("/AddBook1/AddBook1.fxml"));
        FXMLLoader deleteBookLoader = new FXMLLoader(getClass().getResource("/DeleteBook/DeleteBook.fxml"));
        try {
            addBookRoot.getChildren().setAll(Collections.singleton(addBookLoader.load()));
            deleteBookRoot.getChildren().setAll(Collections.singleton(deleteBookLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
