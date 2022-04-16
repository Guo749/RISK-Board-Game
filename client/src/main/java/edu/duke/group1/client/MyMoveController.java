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
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MyMoveController implements Initializable {


    /* the region which issues the move */
    ObservableList<String> froms    = FXCollections.observableArrayList();

    /* the region which receives the move operation */
    ObservableList<String> tos      = FXCollections.observableArrayList();

    /* the number of the units that can be moved */
    ObservableList<String> unitNums = FXCollections.observableArrayList();

    /* the level of the units that can be moved*/
    ObservableList<String> level    = FXCollections.observableArrayList();

    /* the player info for this round */
    public static PlayerInfo info = null;

    /* the place user chooses to begin movement */
    @FXML
    ChoiceBox from;

    /* the place user choose to move to  */
    @FXML
    ChoiceBox to;

    /* the unit number user choose to issue the move */
    @FXML
    ChoiceBox unitNum;

    /* the level for user to choose to issue the move */
    @FXML
    ChoiceBox unitLevel;


    @FXML
    TextArea textArea;

    @FXML
    ImageView myView;


    @FXML
    Button btn1;

    @FXML
    Button btn2;

    @FXML
    Button btn3;

    @FXML
    Button btn4;

    @FXML
    Button btn5;

    @FXML
    Button btn6;



    @FXML
    public void showContent(MouseEvent ae){
        Helper.showContent(ae, info, textArea);
    }

    @FXML
    public void hideContent(){
        //textArea.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        froms.clear();
        tos.clear();
        unitNums.clear();
        level.clear();

        //Helper.initializeBtn(info, info.getMap(),  btn1,  btn2,  btn3,  btn4,  btn5,  btn6);

        int mapNum = Helper.initializeMapNum(info);
        myView.setImage(Helper.initializeMap(mapNum));
        for(Region region : Helper.getAllRegions(info))
            froms.add(region.getName());
        from.setItems(froms);

        for(Region region : Helper.getAllRegions(info))
            tos.add(region.getName());

        to.setItems(tos);

        for(int i = 1; i <= Helper.getUnitsNum(info); i++)
            unitNums.add(String.valueOf(i));
        unitNum.setItems(unitNums);

        for(int i = 0; i <= 6; i++)
            level.add(String.valueOf(i));
        level.add("SPY");
        unitLevel.setItems(level);

       // TextView myView = new TextView(info.getMap());
        //String display = myView.displayRegionInString(info.getPlayerId(), true);
       // textArea.setText(display);
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
        String myFrom    = ((String) from.getValue()     ) == null ? "RandomValue" : (String)from.getValue()     ;
        String myTo      = ((String) to.getValue()       ) == null ? "RandomValue" : (String)to.getValue()       ;
        String myUnitNum = ((String) unitNum.getValue()  ) == null ? "RandomValue" : (String)unitNum.getValue()  ;
        String myLevel   = ((String) unitLevel.getValue()) == null ? "RandomValue" : (String)unitLevel.getValue();

        Action action = null;
        if(!Player.isAttack) action = new Move(info.getMap().getPlayerMapping(info.getPlayerId()),
                myFrom, myTo, Helper.parseInt(myUnitNum), Helper.parseInt(myLevel), myLevel.equals("SPY"));
        else
            action = new Attack(info.getMap().getPlayerMapping(info.getPlayerId()),
                    myFrom, myTo, Helper.parseInt(myUnitNum), Helper.parseInt(myLevel));

        Helper.sendAction(action);
    }

    @FXML
    public void cancel() throws IOException {
        App.ps.setScene(MyPlayController.show(info));
    }

    @FXML
    public void hitSwitch() throws Exception {
        Player.actionStringList.clear();
        Action login = new LoginAction(info.getAccount(), info.getPassword());
        Helper.sendAction(login);
    }

    /**
     * show the page of moving
     *
     * @param playerInfo the player info to be injected
     * @return
     * @throws IOException
     */
    public static Scene show(PlayerInfo playerInfo) throws IOException {
        info = playerInfo;
        return Helper.getScene("/fxml/MyMove.fxml");
    }

}
