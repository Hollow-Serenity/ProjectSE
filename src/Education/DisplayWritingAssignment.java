package Education;
import Main.Menu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DisplayWritingAssignment {
    private final HBox hBox = new HBox(10);
    private final HBox hBox1 = new HBox(5);
    private final GridPane gridPane = new GridPane();

    private final WritingAssignment writingAssignment = new WritingAssignment();

    private final Label questionLabel = new Label(writingAssignment.getRandomQuestion().getText());
    private final Label checkLabel = new Label();
    private final Label checkAnswerFirstLabel = new Label();
    private final TextField responseTextField = new TextField();

    private final Button randomizeButton = new Button("next");
    private final Button checkAnswer = new Button("check");

    private void setLayout() {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setMinSize(800, 800);

        gridPane.add(hBox, 0, 0);
        gridPane.add(checkAnswer, 3, 0);
        gridPane.add(hBox1, 3, 2);

        hBox.getChildren().addAll(questionLabel, responseTextField, checkLabel);
        hBox1.getChildren().addAll(randomizeButton, checkAnswerFirstLabel);
    }

    private void setRandomizeButton() {
        if(!checkLabel.getText().equals("")) {
            questionLabel.setText(writingAssignment.getRandomQuestion().getText());
            responseTextField.setText("");
            checkLabel.setText("");
        } else {
            checkAnswerFirstLabel.setText("Please check your answer first");
        }
    }

    private void setAnswerButton() {
        checkAnswerFirstLabel.setText("");
        if(writingAssignment.getCurrentQuestion().checkAnswer(responseTextField.getText())) {
            checkLabel.setText("correct");
            checkLabel.setTextFill(Color.GREEN);
        } else {
            checkLabel.setText("incorrect");
            checkLabel.setTextFill(Color.RED);
        }
    }

    public DisplayWritingAssignment(BorderPane layout) {
        setLayout();

        layout.setCenter(gridPane);
        layout.setTop(Menu.getMenu(layout));

        randomizeButton.setOnAction(actionEvent -> setRandomizeButton());
        checkAnswer.setOnAction(actionEvent -> setAnswerButton());
    }
}
