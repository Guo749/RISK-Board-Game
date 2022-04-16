package edu.duke.group1.server;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.duke.group1.shared.*;

/*
 * A player's thread, communicate with client socket
 */
public class PlayerController extends Thread{

    /* player's socket to communicate with others*/
    private Socket playerSocket;

    /* the app in the server side*/
    private App app;

    /* the input stream for this player in the server side */
    private ObjectInputStream inputStream;

    /* the output stream for this player in the client side */
    private ObjectOutputStream outputStream;

    /* the ID for this player */
    private int playerId;

    /**
     * Constructs a player controller object
     *
     * @param playerSocket is the player client's socket
     * @param app the main server
     */
    public PlayerController(Socket playerSocket, App app) throws IOException {
        this.playerSocket = playerSocket;//set up input and output socket with the player client
        this.inputStream = new ObjectInputStream(playerSocket.getInputStream());
        this.outputStream = new ObjectOutputStream(playerSocket.getOutputStream());
        this.app = app;
        this.playerId = -1;
    }


    /**
     * player controller runs a loop until the client exit
     * it will read a action from the socket, send it to app to process it
     * then it will wait a lock on the room, waiting for other players to send action
     * when all the players finished, it will send the player's player info back to the client
     */
    @Override
    public void run(){
        try {
            //createPlayer();
            login(); // replace it with createPlayer() for evo2
            while(true){
                Action action = (Action)inputStream.readObject();//read action
                app.processAction(action, playerId);
                int roomId = app.getPlayerInfo(playerId).getCurrRoomId();
                if(roomId != -1) {
                    Room room = app.getRoomById(roomId);
                    if (app.getPlayerInfo(playerId).getNeedWait()) {// the player need to wait for others
                        synchronized (room.getPlayerLock()) {//wait other player
                            room.getPlayerLock().wait();
                        }
                        PlayerInfo playerInfo = app.getPlayerInfo(playerId);
                        playerInfo.setNeedWait(false);
                    }
                }
                //System.out.println(app.getPlayerInfo(playerId).getStatus());
                outputStream.writeObject(app.getPlayerInfo(playerId));//send playerinfo
                outputStream.reset();
            }

        }catch(Exception e){
            e.printStackTrace();
            if(playerId != -1) {
                app.getPlayerInfo(playerId).setOnline(false);
            }
        }
    }

    /**
     * player controller runs a loop until the client login successfully
     * it will read a action from the socket, send it to app to process it
     * when the playerinfo status is selectroom, it will go to select room stage
     *
     */
    void login() throws IOException, ClassNotFoundException {

        while(true){
            Action action = (Action)inputStream.readObject();
            PlayerInfo playerInfo = app.processActionOutOfRoom(action);
            outputStream.writeObject(playerInfo);//send playerinfo
            outputStream.reset();
            this.playerId = playerInfo.getPlayerId();
            if(!playerInfo.getStatus().equals("loginOrRegister")){
                break;
            }
        }
    }




}



