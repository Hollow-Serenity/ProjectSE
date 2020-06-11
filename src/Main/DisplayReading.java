package Main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DisplayReading extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Reading reading = new Reading();
        reading.getRandomReadingAssignment();
        ReadingAssignment readingAssignment = reading.getCurrentReadingAssignment();

        HBox hBox = new HBox(250);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setPadding(new Insets(100, 20, 20, 20));
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);

        HBox checkHBox = new HBox(10);

        Label story = new Label(readingAssignment.getStory());
        Label question1Label = new Label(readingAssignment.getQuestions().get(0).toString());
        TextField response1TextField = new TextField();
        Label question2Label = new Label(readingAssignment.getQuestions().get(1).toString());
        TextField response2TextField = new TextField();
        Label question3Label = new Label(readingAssignment.getQuestions().get(2).toString());
        TextField response3TextField = new TextField();
        Label isCorrectLabel = new Label();

        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(response1TextField);
        textFields.add(response2TextField);
        textFields.add(response3TextField);

        Button checkButton = new Button("check");
        checkButton.setOnAction(actionEvent -> {
            String isCorrect = "correct";
            for(Question question : reading.getCurrentReadingAssignment().getQuestions()) {
                for(TextField textField : textFields) {
                    if(question.checkAnswer(textField.getText())) {
                        isCorrect = "correct";
                    } else {
                        isCorrect = "incorrect";
                    }
                }
            }
            isCorrectLabel.setText(isCorrect);
        });

        Button randomize = new Button("randomize");
        randomize.setOnAction(actionEvent -> {
            reading.getRandomReadingAssignment();
            story.setText(reading.getCurrentReadingAssignment().getStory());
            question1Label.setText(reading.getCurrentReadingAssignment().getQuestions().get(0).toString());
            question2Label.setText(reading.getCurrentReadingAssignment().getQuestions().get(1).toString());
            question3Label.setText(reading.getCurrentReadingAssignment().getQuestions().get(2).toString());
            response1TextField.setText("");
            response2TextField.setText("");
            response3TextField.setText("");

            isCorrectLabel.setText("");
        });

        checkHBox.getChildren().addAll(checkButton, isCorrectLabel);
        hBox.getChildren().add(story);
        vBox.getChildren().addAll(question1Label, response1TextField, question2Label, response2TextField, question3Label, response3TextField, checkHBox, randomize);
        hBox.getChildren().add(vBox);

        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        stage.show();
    }
}
