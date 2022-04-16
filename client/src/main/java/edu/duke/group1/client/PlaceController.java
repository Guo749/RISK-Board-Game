package edu.duke.group1.client;

import edu.duke.group1.shared.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class PlaceController implements Initializable {
    ObservableList<String> places    = FXCollections.observableArrayList();

    ObservableList<String> unitNums  = FXCollections.observableArrayList();

    public static int mapNum = 0;

    public static PlayerInfo info = null;

    @FXML
    ChoiceBox place;

    @FXML
    ChoiceBox unitNum;


    @FXML
    Button submit;

    @FXML
    ImageView myView;

    @FXML
    TextArea textArea;



    @FXML
    ListView actions;


    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        places.clear();
        unitNums.clear();

        actions.setItems(Player.actionStringList);
        Image image = Helper.initializeMap(mapNum);
        myView.setImage(image);

        List<Region> myRegions = Helper.getMyRegions(info);
        for(Region r : myRegions)
            places.add(r.getName());

        place.setItems(places);

        for(int i = 1; i <= info.getMap().getUnplacedUnitsMapping(info.getPlayerId()); i++) {
            unitNums.add(String.valueOf(i));
        }
        unitNum.setItems(unitNums);

        TextView myView1 = new TextView(info.getMap());
        String display = myView1.displayRegionInString(info.getPlayerId(), true);
        textArea.setText(display);
    }

    @FXML
    public void hitSwitch() throws Exception {
        Action login = new LoginAction(info.getAccount(), info.getPassword());
        Helper.sendAction(login);
    }

    /**
     * used to record the hit button
     */
    @FXML
    public void userSubmit() throws Exception {
        Action action = null;

        String myPlace      = (String) place.getValue();
        String unitNumValue = (String) unitNum.getValue();

        action = new Place(info.getMap().getPlayerMapping(info.getPlayerId()), myPlace, Helper.parseInt(unitNumValue), 0);

        Helper.sendAction(action);
    }



    public static Scene show(PlayerInfo playerInfo) throws IOException {
        info = playerInfo;
        mapNum = Helper.initializeMapNum(info);
        return Helper.getScene("/fxml/place.fxml");
    }


    public void initializeCircle(AbstractMap map) {

    }
}
