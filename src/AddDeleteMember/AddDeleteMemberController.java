package AddDeleteMember;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader addMemberLoader = new FXMLLoader(getClass().getResource("/AddMember/AddMember.fxml"));
        FXMLLoader deleteMemberLoader = new FXMLLoader(getClass().getResource("/DeleteMember/DeleteMember.fxml"));
        try {
            addMemberRoot.getChildren().setAll(Collections.singleton(addMemberLoader.load()));
            deleteMemberRoot.getChildren().setAll(Collections.singleton(deleteMemberLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
