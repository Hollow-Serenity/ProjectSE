package Main;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.util.Random;
import java.util.ArrayList;

public class Question {
    private String text;
    private String answer;
    private TextField textField = new TextField();
    private GridPane gridPane = new GridPane();

    public Question(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }
    public Question() {
    }

    public void makeMathExpression(){
        Random rand = new Random();
        final char[] OPERATOR_LIST = {'+','-','x','/'};
        int getal1 = rand.nextInt(10)+1;
        int getal2 = rand.nextInt(10)+1;
        int getal3 = rand.nextInt(4);
        if(getal3 == 3) {
            getal1 = getal2*(rand.nextInt(11)+1);
        }
        if(getal2 > getal1) {
            int temp = getal2;
            getal2 = getal1;
            getal1 = temp;
        }
        setText(getal1 + ""+OPERATOR_LIST[getal3]+ getal2);
        setAnswer(String.valueOf(calculateMathExpression(getal1,getal2,OPERATOR_LIST[getal3])));
    }
    public static int calculateMathExpression(int getal1, int getal2, char operator) {
        switch (operator) {
            case '+':
                return getal1 + getal2;
            case '-':
                return getal1 - getal2;
            case 'x':
                return getal1 * getal2;
            case '/':
                return getal1/getal2;
            default:
                return 0;
        }
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

    public TextField getTextField() {
        return textField;
    }
    public void setTextFieldText(String text) { //For testing
        textField.setText(text);
    }

    public Boolean checkAnswer() {
        String responseString = textField.getText();
        return responseString.equals(answer);
    }

    public GridPane getGridPane() {
        gridPane.setVgap(10);
        gridPane.add(new Label(getText()), 0, 0);
        gridPane.add(textField,  0, 1);
        return gridPane;
    }

    @Override
    public String toString() {
        return text;
    }
}


class ChoiceQuestion extends Question {
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();
    private GridPane gridPane = new GridPane();
    private HBox hBox = new HBox(10);

    public void addChoice(RadioButton choice, Boolean correct) {
        radioButtons.add(choice);
        hBox.getChildren().add(choice);
        if(correct) {
            setAnswer(choice.getText());
        }
    }

    public Boolean checkAnswer() {
        boolean correct = false;
        for(RadioButton radioButton : radioButtons) {
            if(radioButton.isSelected()) {
                correct = radioButton.getText().equals(getAnswer());
            }
        }
        return correct;
    }

    public GridPane getGridPane() {
        gridPane.setVgap(10);
        gridPane.add(new Label(getText()), 0, 0);
        gridPane.add(hBox, 0, 1);
        return gridPane;
    }

    @Override
    public String toString() {
        return getText();
    }
}