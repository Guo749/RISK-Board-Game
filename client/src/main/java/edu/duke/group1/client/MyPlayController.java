package edu.duke.group1.client;

import edu.duke.group1.shared.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MyPlayController implements Initializable {

    public static PlayerInfo info = null;
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


    @FXML
    public void hitMove() throws IOException {
        Player.isAttack = false;
        try{
            App.ps.setScene(MyMoveController.show(info));
        }catch (Exception e){

        }

    }

    @FXML
    public void hitAttack() throws IOException {
        Player.isAttack = true;
        try{
            App.ps.setScene(MyMoveController.show(info));
        }catch (Exception e){

        }

    }

    @FXML
    public void hitUpgrade() throws IOException {
        try{
            App.ps.setScene(MyUpgradeController.show(info));
        }catch (Exception e){

        }

    }

    @FXML
    public void hitFinish() throws Exception {
        //Player.actionStringList.clear();
        Action finish = new Finish();
        Helper.sendAction(finish);
    }

    @FXML
    public void hitSwitch() throws Exception {
       Helper.hitSwitch(info);
    }

    @FXML
    public void hitTechLevel() throws Exception {
        Action action = new UpgradeTechAction(info.getMap().getPlayerMapping(info.getPlayerId()));

        Helper.sendAction(action);
    }


    public static Scene show(PlayerInfo playerInfo) throws IOException {
        info = playerInfo;
        return Helper.getScene("/fxml/MyPlay.fxml");
    }

    @FXML
    public void showContent(MouseEvent ae){
        Helper.showContent(ae, info, textArea);
    }

    @FXML
    public void hideContent(){
        Helper.hideContent(textArea);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

       // Helper.initializeBtn(info, info.getMap(), btn1, btn2, btn3, btn4, btn5, btn6);

        myView.setImage(Helper.initializeMap(Helper.initializeMapNum(info)));
     }


}
