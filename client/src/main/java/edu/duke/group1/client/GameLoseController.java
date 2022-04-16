package edu.duke.group1.client;

import edu.duke.group1.shared.Action;
import edu.duke.group1.shared.Finish;
import edu.duke.group1.shared.LoginAction;
import edu.duke.group1.shared.PlayerInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameLoseController implements Initializable {

    public static PlayerInfo info = null;

    public static boolean gameOver = false;

    @FXML
    ImageView myView;

    @FXML
    TextArea textArea;

    @FXML
    Button refresh;

    @FXML
    public static Scene show(PlayerInfo playerInfo) throws IOException {
        info = playerInfo;
        return Helper.getScene("/fxml/gameLose.fxml");
    }

    @FXML
    public void hitSwitch() throws Exception {
        Action login = new LoginAction(info.getAccount(), info.getPassword());
        Helper.sendAction(login);
    }

    @FXML
    public void hitRefresh() throws Exception {
        Action action = new Finish();
        Helper.sendAction(action);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(gameOver)
            refresh.setVisible(false);
        else
            refresh.setVisible(true);

        myView.setImage(Helper.initializeMap(Helper.initializeMapNum(info)));
        TextView view = new TextView(info.getMap());
        String display = view.displayRegionInString(info.getPlayerId(), true);
        textArea.setText(display);
    }
}
