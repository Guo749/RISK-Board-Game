package edu.duke.group1.client;

import edu.duke.group1.shared.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * This is used to handle the event for the user to join / create room
 */
public class ChooseRoomController implements Initializable{

    /* the map for users to create */
    public static ObservableList<String> createLists = FXCollections.observableArrayList("Create Map2", "Create Map3", "Create Map4", "Create Map5");

    /* waiting rooms, wait for other persons to join */
    public static ObservableList<String> waitingRooms = FXCollections.observableArrayList();

    /* available rooms, read from player-info */
    public static Map<Integer, String> availRooms =  new HashMap<>();

    public static PlayerInfo info = null;

    /* the counter part for roomToCreat in ListView */
    @FXML
    ListView roomToCreate;

    /* the counter part for roomToWait in ListView */
    @FXML
    ListView roomToWait;

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

        //URL xmlResource = ChooseRoomController.class.getResource("/fxml/chooseGame.fxml");
        URL xmlResource = ChooseRoomController.class.getResource("/fxml/showAllRooms.fxml");
        assert(xmlResource != null);
        AnchorPane ap = FXMLLoader.load(xmlResource);


        return new Scene(ap, 640, 480);
    }

    /**
     * This is used to handle when user click the button create
     *
     * @throws Exception
     */
    @FXML
    public void create() throws Exception {
        String item = (String) roomToCreate.getSelectionModel().getSelectedItem();
        if(item == null)
            return;

        Action action = Helper.getChooseNumAction(item);


        Helper.sendAction(action);
    }


    /**
     * This is used to handle when user click the button join
     *
     * @throws Exception
     */
    @FXML
    public void join() throws Exception {

        String item = (String) roomToWait.getSelectionModel().getSelectedItem();
        if(item == null)
            return;
        Action action = null;

        for(Integer roomID : availRooms.keySet()){
            if(availRooms.get(roomID).equals(item)){
                action = new JoinRoomAction(roomID);
                App.player.outputStream.writeObject(action);
                App.player.outputStream.reset();

                App.player.run();
            }
        }

    }

    @FXML
    public void refresh() throws Exception {
        Action login = new LoginAction(info.getAccount(), info.getPassword());
        Helper.sendAction(login);
    }

    /**
     * used to initialize the ListView of Waiting room
     * Get Data from info that server sends
     *
     * @param waitingRooms the rooms that can join at the time of showing this page
     * @param info the player info at this time
     */
    private static void initializeWaitingRoom(ObservableList<String> waitingRooms, PlayerInfo info) {
        waitingRooms.clear();
        availRooms = info.getAvailableRooms();
        if(availRooms == null)
            return;
        for(Integer roomID : availRooms.keySet())
            waitingRooms.add(availRooms.get(roomID));
    }


    /**
     * used to initialize the ListViews for both roomToCreate and roomToWait components
     * called first when FXMLoaded loads it.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeWaitingRoom(waitingRooms, info);

        roomToCreate.setItems(createLists);
        roomToCreate.getSelectionModel().select(0);
        roomToWait.setItems(waitingRooms);
        roomToWait.getSelectionModel().select(0);
    }
}
