package edu.duke.group1.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.duke.group1.shared.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;

/*
 * Player client controller
 */
public class Player {

    /* the socket for each player */
    protected Socket playerSocket;

    /* input stream, used to read information from server side */
    protected ObjectInputStream inputStream;

    /* output stream, used to write information to the server */
    protected ObjectOutputStream outputStream;

    /* the list to store actions a player performed */
    public static  ObservableList<String> actionStringList;

    /* the ip of the server */
    protected String ipAddr;

    /* the port of the server */
    protected int port;

    /* this is to see if this action is move / attack */
    public static volatile boolean  isAttack = false;


    /**
     * Constructs a player object
     *
     * @param ipAddr is the server's ip address
     * @param port is the server's port
     */
    public Player(String ipAddr, int port) throws IOException{
        //set up the socket and it's input and output stream

        this.ipAddr = ipAddr;
        this.port = port;

        try{
            this.playerSocket = new Socket(ipAddr, port);
        }catch(IOException e){
            System.out.println("Connection failed");
            System.exit(0);
        }

        outputStream = new ObjectOutputStream(playerSocket.getOutputStream());
        inputStream = new ObjectInputStream(playerSocket.getInputStream());
        actionStringList = FXCollections.observableArrayList();
    }

    /**
     * This is used to test
     * todo: only for testing
     *
     * @param ipAddr
     * @param port
     * @param test
     * @throws IOException
     */
    public Player(String ipAddr, int port, boolean test) throws IOException{
        //set up the socket and it's input and output stream
        System.out.println("In Player, 333");
        try{
            this.playerSocket = new Socket(ipAddr, port);
        }catch(IOException e){
            System.out.println("Connection failed");
            System.exit(0);
        }

    }


    /**
     * The main function to receive the playerinfo from the server
     * and show the corresponding scene
     */
    public void run() throws Exception {
        System.out.println("In Run");

        WaitController wc = new WaitController();
        Scene scene1 = wc.show();

        App.ps.setScene(scene1);



        Task<PlayerInfo> task = new Task<>() {//recieve a player info using a new thread
            @Override
            protected PlayerInfo call() throws Exception {
                PlayerInfo info = null;
                try {
                    info = (PlayerInfo) inputStream.readObject();
                    if (info == null)
                        return null;
                    return info;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        task.setOnSucceeded(event -> {//having the player info, display the corresponding page
            try {
                PlayerInfo info = task.getValue();
                AbstractMap map = info.getMap();
                if(map != null)
                    map.consumeCloak();

                if (info.getStatus().equals("loginOrRegister")) {
                    Scene scene = LoginController.show();
                    if (App.ps != null)
                        App.ps.setScene(scene);
                } else if (info.getStatus().equals("selectRoom")) {
                    Scene chooseScene = MyChooseRoomController.show(info);
                    if (App.ps != null)
                        App.ps.setScene(chooseScene);
                } else if (info.getStatus().equals("doPlacement")) {
                    System.out.println("------------" + info.getMap().getUnplacedUnitsMapping(info.getPlayerId()));
                    Scene scene = MyPlaceController.show(info);
                    if (App.ps != null)
                        App.ps.setScene(scene);
                } else if (info.getStatus().equals("doAttack")) {
                    Scene scene = MyPlayController.show(info);
                    if (App.ps != null)
                        App.ps.setScene(scene);
                } else if (info.getStatus().equals("gameOver")) {
                    GameLoseController.gameOver = true;
                    App.ps.setScene(GameLoseController.show(info));
                } else if (info.getStatus().equals("gameLose")) {
                    GameLoseController.gameOver = false;
                    App.ps.setScene(GameLoseController.show(info));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        new Thread(task).start();
    }

}
