package Education;

import Main.Menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DisplayReading {
    private final BorderPane Layout;
    private final HBox hBox = new HBox(250);
    private final HBox checkHBox = new HBox(10);
    private final VBox vBox = new VBox(10);

    private final Reading reading = new Reading();

    private final Label isCorrectLabel = new Label();
    private final Label story = new Label();
    private final Label question1Label = new Label();
    private final Label question2Label = new Label();
    private final Label question3Label = new Label();

    private final TextField response1TextField = new TextField();
    private final TextField response2TextField = new TextField();
    private final TextField response3TextField = new TextField();

    private static final Button checkButton = new Button("check");
    private static final Button nextButton = new Button("next");

    private void setLayout() {
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setPadding(new Insets(100, 20, 20, 20));
        vBox.setAlignment(Pos.TOP_LEFT);

        checkHBox.getChildren().addAll(checkButton, isCorrectLabel);
        hBox.getChildren().add(story);
        vBox.getChildren().addAll(question1Label, response1TextField, question2Label, response2TextField, question3Label, response3TextField, checkHBox, nextButton);
        hBox.getChildren().add(vBox);

        Layout.setCenter(hBox);
        Layout.setTop(Menu.getMenu(Layout));
    }

    private void checkButtonAction() {
        String isCorrect;
        if(reading.getCurrentReadingAssignment().checkAnswers(response1TextField.getText(), response2TextField.getText(), response3TextField.getText())) {
            isCorrect = "correct";
            isCorrectLabel.setTextFill(Color.GREEN);
        } else {
            isCorrect = "incorrect";
            isCorrectLabel.setTextFill(Color.RED);
        }
        isCorrectLabel.setText(isCorrect);
    }

    private void nextButtonAction() {
        reading.getRandomReadingAssignment();
        story.setText(reading.getCurrentReadingAssignment().getStory());

        question1Label.setText(reading.getCurrentReadingAssignment().getQuestions().get(0).toString());
        question2Label.setText(reading.getCurrentReadingAssignment().getQuestions().get(1).toString());
        question3Label.setText(reading.getCurrentReadingAssignment().getQuestions().get(2).toString());

        response1TextField.setText("");
        response2TextField.setText("");
        response3TextField.setText("");

        isCorrectLabel.setText("");
    }

    public DisplayReading(BorderPane layout) {
        Layout = layout;
        nextButtonAction();
        setLayout();

        checkButton.setOnAction(actionEvent -> checkButtonAction());
        nextButton.setOnAction(actionEvent -> nextButtonAction());
    }
}
