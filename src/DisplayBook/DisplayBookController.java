package DisplayBook;

import Database.DatabaseHandler;
import EditBook.EditBookController;
import Util.AssistantUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayBookController implements Initializable {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private StackPane rootPane;
    private ArrayList<RenderedBook> bookList = new ArrayList<>();
    @FXML
    private TableColumn<RenderedBook, String> idCol;
    @FXML
    private TableColumn<RenderedBook, String> titleCol;
    @FXML
    private TableColumn<RenderedBook, String> authorCol;
    @FXML
    private TableColumn<RenderedBook, String> publisherCol;
    @FXML
    private TableColumn<RenderedBook, String> intCodeCol;
    @FXML
    private TableColumn<RenderedBook, Boolean> isAvailCol;
    @FXML
    private TableView<RenderedBook> tableView;
    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initCol();
            loadData();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        intCodeCol.setCellValueFactory(new PropertyValueFactory<>("intCode"));
        isAvailCol.setCellValueFactory(new PropertyValueFactory<>("available"));
    }

    public void loadData() {
        ArrayList<RenderedBook> books = DatabaseHandler.searchAllBooks();
        if (books != null) {
            this.bookList = books;
            tableView.getItems().setAll(this.bookList);
        } else {
            System.out.println("No books found");
        }
    }


    @FXML
    public void handleSearch(ActionEvent keyEvent) {
        ObservableList<RenderedBook> filteredList = FXCollections.observableArrayList();

        // Get the search text from the search field
        String searchText = searchField.getText().toLowerCase();

        // Loop through the data and add matching items to the filtered list
        for (RenderedBook book : bookList) {
            if (book.getTitle().toLowerCase().contains(searchText) ||
                    book.getBookId().toLowerCase().contains(searchText) ||
                    book.getAuthor().toLowerCase().contains(searchText) ||
                    book.getPublisher().toLowerCase().contains(searchText) ||
                    book.getIntCode().toLowerCase().contains(searchText) ||
                    String.valueOf(book.getAvailable()).toLowerCase().contains(searchText)) {
                filteredList.add(book);
            }
        }

        // Display the filtered list in the table view
        tableView.getItems().clear();
        tableView.getItems().setAll(filteredList);
    }

    @FXML
    public void handleEdit(ActionEvent keyEvent) throws IOException {
        RenderedBook selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            // No member selected, show an alert and return
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Please select a member to edit.");
            return;
        }

        // Load the edit member view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditBook/EditBook.fxml"));
        Parent root = loader.load();

        // Get the controller for the edit member view and set the member to edit
        EditBookController controller = loader.getController();
        controller.setBook(selectedBook);

        // Create the edit member stage and show it
        Stage stage = new Stage();
        controller.setDialogStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Member");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        // Refresh the table view
        handleSearch(new ActionEvent());
    }
}