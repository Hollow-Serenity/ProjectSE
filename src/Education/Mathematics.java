package Education;

import Main.Login;
import Main.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Mathematics implements EventHandler<ActionEvent> {
    public Stage stage;
    private Pane layout = new Pane(mathExpression,answer,submit,error);
    private Scene scene = new Scene(layout,600, 400);
    private static Label mathExpression = new Label();
    private static Label error = new Label("Your answer is incorrect");
    private static TextField answer = new TextField();
    private static Button submit = new Button("submit");
    private static Question math = new Question();


    public Mathematics(){
        math.makeMathExpression();
        mathExpression.setText(math.getText());
        frontend();
        layout.getStyleClass().add("hbox");
        Login.getLayout().setCenter(layout);
        submit.setOnAction(this);
    }

        private static void frontend(){
            error.setTextFill(Color.RED);
            error.setVisible(false);
            submit.setLayoutX(402.0);
            submit.setLayoutY(166.0);
            answer.setMaxWidth(60);
            answer.setLayoutX(270.0);
            answer.setLayoutY(166.0);
            error.setLayoutX(250.0);
            error.setLayoutY(190);
            mathExpression.setLayoutX(158.0);
            mathExpression.setLayoutY(166.0);
            Login.getLayout().setTop(Menu.getMenu());
        }
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource() == submit){
                if(answer.getText().equals(math.getAnswer())){
                    error.setVisible(false);
                    answer.setText("");
                    math.makeMathExpression();
                    mathExpression.setText(math.getText());
                }
                else {
                    error.setVisible(true);
                }
            }
        }
}