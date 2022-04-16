package edu.duke.group1.client;

import edu.duke.group1.shared.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * This controller uses to controll the game flow of the game
 * user can choose move / attack / upgrade / switch rooom button to do what they want
 */
public class PlayController implements Initializable {

    /* move button */
    @FXML
    Button move;

    /* attack button */
    @FXML
    Button attack;

    /* upgrade button */
    @FXML
    Button upgrade;

    /* finish button */
    @FXML
    Button finish;

    /* switch room button */
    @FXML
    Button switchRoom;

    /* represent the text area, showing the hint info */
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
    Circle circle4;

    @FXML
    Circle circle5;

    @FXML
    Circle circle6;

    @FXML
    ListView actions;


    public static boolean robot = false;

    /* player info for this round */
    public static PlayerInfo info = null;



    public static Scene show(PlayerInfo playerInfo) throws IOException {
        info = playerInfo;
        return Helper.getScene("/fxml/play.fxml");
    }

    @FXML
    public void hitMove() throws IOException {
        Player.isAttack = false;
        try{
            App.ps.setScene(MoveController.show(info));
        }catch (Exception e){

        }

    }

    @FXML
    public void hitAttack() throws IOException {
        Player.isAttack = true;
        try{
            App.ps.setScene(AttackController.show(info));
        }catch (Exception e){

        }

    }

    @FXML
    public void hitUpgrade() throws IOException {
        try{
            App.ps.setScene(UpgradeController.show(info));
        }catch (Exception e){

        }

    }

    @FXML
    public void hitFinish() throws Exception {
        Player.actionStringList.clear();
        Action finish = new Finish();
        Helper.sendAction(finish);
    }

    @FXML
    public void hitSwitch() throws Exception {
        Player.actionStringList.clear();
        Action login = new LoginAction(info.getAccount(), info.getPassword());
        Helper.sendAction(login);
    }

    @FXML
    public void hitTechLevel() throws Exception {
        Action action = new UpgradeTechAction(info.getMap().getPlayerMapping(info.getPlayerId()));

       Helper.sendAction(action);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        myView.setImage(Helper.initializeMap(Helper.initializeMapNum(info)));
        TextView view = new TextView(info.getMap());
        String display = view.displayRegionInString(info.getPlayerId(), true);
        textArea.setText(display);
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
