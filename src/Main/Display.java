package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;

public class Display extends Application {
    private HBox hBox = readingAssignment();
    private Button button = new Button("randomize");

    private HBox readingAssignment() {
        Assignments assignments = new Assignments();

        Random random = new Random();
        int randInt = random.nextInt(assignments.allStories().size());

        Story story = assignments.allStories().get(randInt);

        return story.display();
    }

    public void display() {
        hBox.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            hBox = readingAssignment();

            //hBox.getChildren().add(button);

            //Scene scene = new Scene(hBox1);
            //stage.setScene(scene);
            //stage.show();
        });

        //Scene scene = new Scene(hBox);
        //stage.setScene(scene);
        //stage.show();
    }
    @Override
    public void start(Stage stage) throws Exception {
        display();

        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        stage.show();
    }
}