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
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MoveController implements Initializable {

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

    /* the button for submit */
    @FXML
    Button submit;

    @FXML
    TextArea textArea;

    @FXML
    ImageView myView;

    @FXML
    Circle circle1;

    @FXML
    Circle circle2;

    @FXML
    Circle circle3;

    @FXML
    Circle circle6;


    @FXML
    Circle circle5;

    @FXML
    Circle circle4;

    @FXML
    ListView actions;



    /**
     * This is used to initialize the listview when begin presenting this page
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        froms.clear();
        tos.clear();
        unitNums.clear();
        level.clear();

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

        TextView myView = new TextView(info.getMap());
        String display = myView.displayRegionInString(info.getPlayerId(), true);
        textArea.setText(display);
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
        String myFrom    = (String) from.getValue();
        String myTo      = (String) to.getValue();
        String myUnitNum = (String) unitNum.getValue();
        String myLevel   = (String) unitLevel.getValue();

        Action action = new Move(info.getMap().getPlayerMapping(info.getPlayerId()),
                    myFrom, myTo, Helper.parseInt(myUnitNum), Helper.parseInt(myLevel), myLevel.equals("SPY"));

        Helper.sendAction(action);
    }

    @FXML
    public void cancel() throws IOException {
        App.ps.setScene(PlayController.show(info));
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
        return Helper.getScene("/fxml/move.fxml");
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
