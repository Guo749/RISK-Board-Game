package edu.duke.group1.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WaitController implements Initializable {

    @FXML
    ImageView myView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(WaitController.class.getResource("/fxml/waiting.gif").toString());


        myView.setImage(image);
    }

    @FXML
    public Scene show() throws IOException {
        Scene scene = Helper.getScene("/fxml/wait.fxml", 330, 600);

        //teffe
        return scene;
    }
}

