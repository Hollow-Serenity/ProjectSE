/*window = primaryStage;
        Label label1 = new Label("Resolutie 1");
        Button button1 = new Button("Ga naar resolutie 2");
        button1.setOnAction(e -> window.setScene(scene2));

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 800, 600);

        Button button2 = new Button("Ga naar resolutie 1");
        button2.setOnAction(e -> window.setScene(scene1));

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene (layout2, 600, 300);

        window.setScene(scene1);
        window.setTitle("Titel");
        window.show();*/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/Scene1.fxml"));
        Scene scene = new Scene(root,800,600);
        scene.getStylesheets().add("GUI/Light.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
