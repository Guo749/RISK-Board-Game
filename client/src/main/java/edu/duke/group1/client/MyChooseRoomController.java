package edu.duke.group1.client;


import edu.duke.group1.shared.Action;
import edu.duke.group1.shared.JoinRoomAction;
import edu.duke.group1.shared.LoginAction;
import edu.duke.group1.shared.PlayerInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


public class MyChooseRoomController implements Initializable {
    public static PlayerInfo info = null;

    /* waiting rooms, wait for other persons to join */
    public static ObservableList<String> waitingRooms = FXCollections.observableArrayList();


    public static Map<Integer, String> availableRooms = null;

    @FXML
    ListView waitRooms;

    @FXML
    Button join;


    /**
     * This is to give the primary stage a scene to show up
     * the scene is to let users to choose which room they want
     * to join / create
     *
     * @param playerInfo the player info to be passed
     * @return the scene to be set in the main stage
     * @throws IOException
     */
    @FXML
    public static Scene show(PlayerInfo playerInfo) throws IOException {
        info = playerInfo;

        URL xmlResource = ChooseRoomController.class.getResource("/fxml/showAllRooms.fxml");
        assert(xmlResource != null);
        AnchorPane ap = FXMLLoader.load(xmlResource);


        return new Scene(ap, 1280, 800);
    }


    @FXML
    public void create(ActionEvent ae) throws Exception {
        Button button = (Button) ae.getSource();
        String buttonName = button.getText();
        Action action = Helper.getChooseNumAction("Create Map" + (buttonName.charAt(buttonName.length() - 1) - '0'));

        Helper.sendAction(action);
    }

    @FXML
    public void userJoin() throws Exception {
        String item = (String) waitRooms.getSelectionModel().getSelectedItem();
        if(item == null)
            return;
        Action action = null;

        for(Integer roomID : availableRooms.keySet()) {
            if (availableRooms.get(roomID).equals(item)) {
                action = new JoinRoomAction(roomID);
                App.player.outputStream.writeObject(action);
                App.player.outputStream.reset();

                App.player.run();
            }
        }

    }


    @FXML
    public void userRefresh() throws Exception {
        Action login = new LoginAction(info.getAccount(), info.getPassword());
        Helper.sendAction(login);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        waitingRooms.clear();
        availableRooms = info.getAvailableRooms();
        if(availableRooms == null || availableRooms.size() == 0) {
            waitRooms.setVisible(false);
            join.setVisible(false);
            return;
        }

        for(Integer roomID : availableRooms.keySet())
            waitingRooms.add(availableRooms.get(roomID));

        waitRooms.setItems(waitingRooms);
    }
}
