import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Database.DatabaseHandler;
public class Main extends Application {
    public static Parent root;
    public static void Main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
        primaryStage.setTitle("Library Management");
        primaryStage.setScene(new Scene(root, 1300, 1000));
        primaryStage.show();
    }
}
