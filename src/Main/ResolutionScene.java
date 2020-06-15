package Main;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class ResolutionScene {

private Boolean BtnRes = true;
    public void btnResClick() throws IOException {
        if(this.BtnRes) {
            BtnRes = false;
        }
        else {
            BtnRes = true;
        }

        }




    public  Boolean getRes(){
        return BtnRes;
    }


}
//
