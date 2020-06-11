package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;

public class Display extends Application {
    private HBox readingAssignment() {
        Assignments assignments = new Assignments();

        Random random = new Random();
        int randInt = random.nextInt(assignments.allStories().size());

        Story story = assignments.allStories().get(randInt);
        return story.display();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = readingAssignment();

        Button button = new Button("randomize");
        hBox.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            HBox hBox1 = readingAssignment();
            hBox1.getChildren().add(button);

            Scene scene = new Scene(hBox1);
            stage.setScene(scene);
            stage.show();
        });

        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        stage.show();
    }
}