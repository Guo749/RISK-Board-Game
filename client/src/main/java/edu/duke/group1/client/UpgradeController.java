package edu.duke.group1.client;

import edu.duke.group1.shared.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class UpgradeController implements Initializable {

    ObservableList<String> regions    = FXCollections.observableArrayList();

    ObservableList<String> unitNums = FXCollections.observableArrayList();

    ObservableList<String> oldLevel    = FXCollections.observableArrayList();

    ObservableList<String> newLevel    = FXCollections.observableArrayList();

    public static PlayerInfo info = null;

    @FXML
    ChoiceBox region;

    @FXML
    ChoiceBox unitNum;

    @FXML
    ChoiceBox oldLevelBox;

    @FXML
    ChoiceBox newLevelBox;

    @FXML
    ImageView myView;

    @FXML
    TextArea textArea;

    @FXML
    Circle circle1;

    @FXML
    Circle circle2;

    @FXML
    Circle circle3;

    @FXML
    Circle circle4;

    @FXML
    Circle circle5;

    @FXML
    Circle circle6;

    @FXML
    ListView actions;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regions.clear();
        unitNums.clear();
        oldLevel.clear();
        newLevel.clear();


        if(info.getMap().getClass() != MapForTwo.class){
            circle1.setVisible(false);
            circle2.setVisible(false);
            circle3.setVisible(false);
            circle4.setVisible(false);
            circle5.setVisible(false);
            circle6.setVisible(false);
        }else
            initializeCircle(info.getMap());

        actions.setItems(Player.actionStringList);
        int mapNum = Helper.initializeMapNum(info);
        myView.setImage(Helper.initializeMap(mapNum));

        for(Region i : Helper.getMyRegions(info))
            regions.add(i.getName());
        region.setItems(regions);

        for(int i = 1; i <= Helper.getUnitsNum(info); i++)
            unitNums.add(String.valueOf(i));
        unitNum.setItems(unitNums);

        for(int i = 0; i <= 5; i++)
            oldLevel.add(String.valueOf(i));
        oldLevelBox.setItems(oldLevel);

        for(int i = 1; i <= 6; i++)
            newLevel.add(String.valueOf(i));
        newLevel.add("SPY");
        newLevel.add("CLOAK");
        newLevelBox.setItems(newLevel);


        TextView myView = new TextView(info.getMap());
        String display = myView.displayRegionInString(info.getPlayerId(), true);
        textArea.setText(display);
    }

    public static Scene show(PlayerInfo playerInfo) throws IOException {
        info = playerInfo;
        return Helper.getScene("/fxml/upgrade.fxml");
    }

    /**
     * This is used to handle the event when user click the submit
     * We will get the text that user choose
     * then call run to show corresponding page
     *
     * @throws Exception
     */
    @FXML
    public void userSubmit() throws Exception {
        String myRegion    = (String) region.getValue();
        String myUnitNum = (String) unitNum.getValue();
        String myOldLevel   = (String) oldLevelBox.getValue();
        String myNewLevel   = (String) newLevelBox.getValue();

        Action action = null;
        if(myNewLevel.equals("CLOAK")){
            action = new UpgradeAction(info.getMap().getPlayerMapping(info.getPlayerId()),
                    myRegion, 0, 0, 0, false, true);
        }
        else if(myNewLevel.equals("SPY")){
            action = new UpgradeAction(info.getMap().getPlayerMapping(info.getPlayerId()),
                    myRegion, 0, 0, 0, true, false);
        }else {
            action = new UpgradeAction(info.getMap().getPlayerMapping(info.getPlayerId()),
                    myRegion, Helper.parseInt(myUnitNum), Helper.parseInt(myOldLevel), Helper.parseInt(myNewLevel));
        }

        Helper.sendAction(action);

    }

    @FXML
    public void userCancel() throws IOException {
        App.ps.setScene(PlayController.show(info));
    }


    public void initializeCircle(AbstractMap map) {
        MapForTwo map2 = (MapForTwo) map;

        Map<String, Region> regionFinder = map2.getRegionFinder();
        Region Beijing = regionFinder.get("Beijing");
        Region Chengdu = regionFinder.get("Chengdu");
        Region Suzhou = regionFinder.get("Suzhou");
        Region Shanghai = regionFinder.get("Shanghai");
        Region Guangzhou = regionFinder.get("Guangzhou");
        Region Kunming = regionFinder.get("Kunming");


        circle1.setFill(Beijing.getOwnerID() == 1 ? Color.BLUE : Color.YELLOW);
        circle2.setFill(Chengdu.getOwnerID() == 1 ? Color.BLUE : Color.YELLOW);
        circle3.setFill(Suzhou.getOwnerID() == 1 ? Color.BLUE : Color.YELLOW);

        circle4.setFill(Kunming.getOwnerID() == 1 ? Color.BLUE : Color.YELLOW);
        circle5.setFill(Guangzhou.getOwnerID() == 1 ? Color.BLUE : Color.YELLOW);
        circle6.setFill(Shanghai.getOwnerID() == 1 ? Color.BLUE : Color.YELLOW);

    }


}
