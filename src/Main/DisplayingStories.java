package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DisplayingStories {
    public static HBox display(Reading reading) {
        Button checkButton = new Button("Check");
        Label checkLabel = new Label();

        VBox vBox = new VBox(20);
        HBox hBox = new HBox(400);
        hBox.setPadding(new Insets(100, 50, 50, 50));
        hBox.setAlignment(Pos.TOP_CENTER);

        Label story = new Label(reading.getStory());
        hBox.getChildren().add(story);

        for(Question question : reading.getQuestions()) {
            vBox.getChildren().add(question.getGridPane());
        }

        checkButton.setOnAction(actionEvent -> {
            boolean correct = true;
            for(Question question : reading.getQuestions()) {
                if(!question.checkAnswer()){
                    correct = false;
                }
            }
            if(correct) {
                checkLabel.setText("correct");
            } else {
                checkLabel.setText("one or more questions are incorrect");
            }
        });

        HBox checkHBox = new HBox(10);
        checkHBox.getChildren().addAll(checkButton, checkLabel);

        vBox.getChildren().add(checkHBox);
        hBox.getChildren().add(vBox);

        hBox.setMinSize(2000, 1000); //HBox size

        return hBox;
    }
}