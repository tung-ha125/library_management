package DisplayMember;

import Database.DatabaseHandler;
import EditMember.EditMemberController;
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

public class DisplayMemberController implements Initializable {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private StackPane rootPane;
    private ArrayList<RenderedMember> memberList = new ArrayList<>();
    @FXML
    private TableColumn<RenderedMember, String> nameCol;
    @FXML
    private TableColumn<RenderedMember, String> idCol;
    @FXML
    private TableColumn<RenderedMember, String> mobileCol;
    @FXML
    private TableColumn<RenderedMember, String> emailCol;
    @FXML
    private TableView<RenderedMember> tableView;
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
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public void loadData() {
        ArrayList<RenderedMember> members = DatabaseHandler.searchAllMembers();
        if (members != null) {
            this.memberList = members;
            if (tableView.getItems() != null) {
                tableView.getItems().clear();
            }
            tableView.getItems().setAll(this.memberList);
        } else {
            System.out.println("No members found");
        }
    }

    @FXML
    public void handleSearch(ActionEvent keyEvent) {
        ObservableList<RenderedMember> filteredList = FXCollections.observableArrayList();

        // Get the search text from the search field
        String searchText = searchField.getText().toLowerCase();

        // Loop through the data and add matching items to the filtered list
        for (RenderedMember member : memberList) {
            if (member.getName().toLowerCase().contains(searchText) ||
                    member.getMemberId().toLowerCase().contains(searchText) ||
                    member.getMobile().toLowerCase().contains(searchText) ||
                    member.getEmail().toLowerCase().contains(searchText)) {
                filteredList.add(member);
            }
        }

        // Display the filtered list in the table view
        tableView.getItems().clear();
        tableView.getItems().setAll(filteredList);
    }

    public void handleEdit(ActionEvent event) throws IOException {
        RenderedMember selectedMember = tableView.getSelectionModel().getSelectedItem();
        if (selectedMember == null) {
            // No member selected, show an alert and return
            AssistantUtil.displayDialog(rootPane, rootAnchorPane, null, "Please select a member to edit.");
            return;
        }

        // Load the edit member view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditMember/EditMember.fxml"));
        Parent root = loader.load();

        // Get the controller for the edit member view and set the member to edit
        EditMemberController controller = loader.getController();
        controller.setMember(selectedMember);

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