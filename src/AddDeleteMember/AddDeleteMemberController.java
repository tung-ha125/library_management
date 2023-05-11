package AddDeleteMember;

import AddMember.AddMemberController;
import DeleteMember.DeleteMemberController;
import DisplayMember.DisplayMemberController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class AddDeleteMemberController implements Initializable {
    @FXML
    private StackPane addMemberRoot;
    @FXML
    private StackPane deleteMemberRoot;
    private DisplayMemberController display_member_controller;
    private FXMLLoader addMemberLoader;
    private FXMLLoader deleteMemberLoader;
    private AddMemberController add_member_controller;
    private DeleteMemberController delete_member_controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addMemberLoader = new FXMLLoader(getClass().getResource("/AddMember/AddMember.fxml"));
        deleteMemberLoader = new FXMLLoader(getClass().getResource("/DeleteMember/DeleteMember.fxml"));
        try {
            addMemberRoot.getChildren().setAll(Collections.singleton(addMemberLoader.load()));
            deleteMemberRoot.getChildren().setAll(Collections.singleton(deleteMemberLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUpMemberViewController(DisplayMemberController controller) {
        display_member_controller = controller;
        add_member_controller = addMemberLoader.getController();
        delete_member_controller = deleteMemberLoader.getController();
        add_member_controller.setUpDisplayMemberController(display_member_controller);
        delete_member_controller.setUpDisplayMemberController(display_member_controller);
    }
}
