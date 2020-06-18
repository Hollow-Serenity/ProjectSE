package Education;
import Main.Login;
import Main.Menu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DisplayWritingAssignment {
    private WritingAssignment writingAssignment = new WritingAssignment();
    private Label questionLabel = new Label(writingAssignment.getRandomQuestion().getText());
    private TextField responseTextField = new TextField();
    private Label checkLabel = new Label();
    private Label checkAnswerFirstLabel = new Label();

    public DisplayWritingAssignment() {
        HBox hBox = new HBox(10);
        HBox hBox1 = new HBox(5);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setMinSize(800, 800);

        Button randomizeButton = new Button("randomize");
        randomizeButton.setOnAction(actionEvent -> {
            if(!checkLabel.getText().equals("")) {
                questionLabel.setText(writingAssignment.getRandomQuestion().getText());
                responseTextField.setText("");
                checkLabel.setText("");
            } else {
                checkAnswerFirstLabel.setText("Please check your answer first");
            }
        });

        Button checkAnswer = new Button("check");
        checkAnswer.setOnAction(actionEvent -> {
            checkAnswerFirstLabel.setText("");
            if(writingAssignment.getCurrentQuestion().checkAnswer(responseTextField.getText())) {
                checkLabel.setText("correct");
                checkLabel.setTextFill(Color.GREEN);
            } else {
                checkLabel.setText("incorrect");
                checkLabel.setTextFill(Color.RED);
            }
        });

        hBox.getChildren().addAll(questionLabel, responseTextField, checkLabel);
        hBox1.getChildren().addAll(randomizeButton, checkAnswerFirstLabel);
        gridPane.add(hBox, 0, 0);
        gridPane.add(checkAnswer, 3, 0);
        gridPane.add(hBox1, 3, 2);

        Login.getLayout().setCenter(gridPane);
        Login.getLayout().setTop(Menu.getMenu());
    }
}
