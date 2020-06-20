package Education;

import UserManagement.Login;
import Main.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Mathematics implements EventHandler<ActionEvent> {
    public Stage stage;
    private static BorderPane Layout;
    private Pane layout = new Pane(mathExpression,answer,submit,error, counter);
    private Scene scene = new Scene(layout,600, 400);
    private static Label mathExpression = new Label();
    private static Label error = new Label("Your answer is incorrect");
    private static TextField answer = new TextField();
    private static Label counter = new Label("Correct Answers: 0");
    private static Button submit = new Button("submit");
    private static Question math = new Question();
    private static int counterNumber = 0;

    public Mathematics(BorderPane layout1){
        Layout = layout1;
        math.makeMathExpression();
        mathExpression.setText(math.getText());
        frontend();
        layout.getStyleClass().add("hbox");
        Layout.setCenter(layout);
        submit.setOnAction(this);
    }

        private static void frontend(){
            error.setTextFill(Color.RED);
            error.setVisible(false);
            counter.setLayoutX(390);
            counter.setLayoutY(190);
            submit.setLayoutX(402.0);
            submit.setLayoutY(166.0);
            answer.setMaxWidth(60);
            answer.setLayoutX(270.0);
            answer.setLayoutY(166.0);
            error.setLayoutX(250.0);
            error.setLayoutY(190);
            mathExpression.setLayoutX(158.0);
            mathExpression.setLayoutY(166.0);
            Layout.setTop(Menu.getMenu(Layout));
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