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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyUpgradeController implements Initializable {

    ObservableList<String> regions    = FXCollections.observableArrayList();

    ObservableList<String> unitNums = FXCollections.observableArrayList();

    ObservableList<String> oldLevel    = FXCollections.observableArrayList();

    ObservableList<String> newLevel    = FXCollections.observableArrayList();


    /* the player info for this round */
    public static PlayerInfo info = null;

    /* the place user chooses to begin movement */
    @FXML
    ChoiceBox region;

    /* the place user choose to move to  */
    @FXML
    ChoiceBox unitNum;

    /* the unit number user choose to issue the move */
    @FXML
    ChoiceBox oldLevelBox;

    /* the level for user to choose to issue the move */
    @FXML
    ChoiceBox newLevelBox;


    @FXML
    TextArea textArea;

    @FXML
    ImageView myView;


    @FXML
    Ellipse Beijing;

    @FXML
    Ellipse Chengdu;

    @FXML
    Ellipse Suzhou;

    @FXML
    Ellipse Kunming;

    @FXML
    Ellipse Guangzhou;

    @FXML
    Ellipse Shanghai;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  Helper.initializeBtn(info, info.getMap(),  btn1,  btn2,  btn3,  btn4,  btn5,  btn6);

        regions.clear();
        unitNums.clear();
        oldLevel.clear();
        newLevel.clear();


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
    }

    @FXML
    public void hitSwitch() throws Exception {
        Helper.hitSwitch(info);
    }

    @FXML
    public void showContent(MouseEvent ae){
        Helper.showContent(ae, info, textArea);
    }

    @FXML
    public void hideContent(){
        Helper.hideContent(textArea);
    }
    @FXML
    public void userSubmit() throws Exception {
        String myRegion    =  ((String) region.getValue()    ) == null ?  "RandomValue" : (String)region.getValue()    ;
        String myUnitNum    = ((String) unitNum.getValue()    ) == null ? "RandomValue" : (String)unitNum.getValue()    ;
        String myOldLevel   = ((String) oldLevelBox.getValue()) == null ? "RandomValue" : (String)oldLevelBox.getValue();
        String myNewLevel   = ((String) newLevelBox.getValue()) == null ? "RandomValue" : (String)newLevelBox.getValue();

        Action action = null;
        if(myNewLevel.equals("CLOAK")){
            action = new UpgradeAction(info.getMap().getPlayerMapping(info.getPlayerId()),
                    myRegion, 0, 0, 0, false, true);
        }
        else if(myNewLevel.equals("SPY")){
            action = new UpgradeAction(info.getMap().getPlayerMapping(info.getPlayerId()),
                    myRegion, Helper.parseInt(myUnitNum),  Helper.parseInt(myOldLevel), 0, true, false);
        }else {
            action = new UpgradeAction(info.getMap().getPlayerMapping(info.getPlayerId()),
                    myRegion, Helper.parseInt(myUnitNum), Helper.parseInt(myOldLevel), Helper.parseInt(myNewLevel));
        }

        Helper.sendAction(action);
    }

    @FXML
    public void cancel() throws IOException {
        App.ps.setScene(MyPlayController.show(info));
    }

    public static Scene show(PlayerInfo playerInfo) throws IOException {
        info = playerInfo;
        return Helper.getScene("/fxml/MyUpgrade.fxml");
    }
}
