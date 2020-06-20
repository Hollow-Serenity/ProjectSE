package Education;

import Main.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Mathematics implements EventHandler<ActionEvent> {
    private static BorderPane Layout;

    private static final Label mathExpression = new Label();
    private static final Label error = new Label("Your answer is incorrect");
    private static final Label counter = new Label("Correct Answers: 0");

    private static final TextField answer = new TextField();

    private static final Button submit = new Button("submit");

    private static final Question math = new Question();

    private static int counterNumber = 0;

    private static void setLayout(){
        error.setTextFill(Color.RED);
        error.setVisible(false);
        counter.setLayoutX(390);
        counter.setLayoutY(200);
        submit.setLayoutX(402.0);
        submit.setLayoutY(166.0);
        answer.setMaxWidth(60);
        answer.setLayoutX(270.0);
        answer.setLayoutY(166.0);
        error.setLayoutX(200.0);
        error.setLayoutY(200);
        mathExpression.setLayoutX(158.0);
        mathExpression.setLayoutY(166.0);
        Layout.setTop(Menu.getMenu(Layout));
    }

    public Mathematics(BorderPane layout){
        Layout = layout;
        math.makeMathExpression();
        mathExpression.setText(math.getText());
        setLayout();
        Pane pane = new Pane(mathExpression, answer, submit, error, counter);
        pane.getStyleClass().add("hbox");
        Layout.setCenter(pane);
        submit.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == submit){
            if(answer.getText().equals(math.getAnswer())){
                counterNumber++;
                error.setVisible(false);
                answer.setText("");
                math.makeMathExpression();
                mathExpression.setText(math.getText());
                counter.setText("Correct Answers: "+ counterNumber);
            }
            else {
                error.setVisible(true);
            }
        }
    }
}