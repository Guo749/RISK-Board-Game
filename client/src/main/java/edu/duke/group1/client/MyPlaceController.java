package edu.duke.group1.client;

import edu.duke.group1.shared.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyPlaceController implements Initializable {
    ObservableList<String> places    = FXCollections.observableArrayList();

    ObservableList<String> unitNums  = FXCollections.observableArrayList();

    public static int mapNum = 0;

    public static PlayerInfo info = null;

    @FXML
    ChoiceBox place;

    @FXML
    ChoiceBox unitNum;

    @FXML
    ImageView myView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        places.clear();
        unitNums.clear();

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
    }


    @FXML
    public void hitSwitch() throws Exception {
        Helper.hitSwitch(info);
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
        return Helper.getScene("/fxml/MyPlace.fxml");
    }

}
