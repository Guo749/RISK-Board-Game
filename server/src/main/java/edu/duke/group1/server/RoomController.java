package edu.duke.group1.server;

import edu.duke.group1.shared.PlayerInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/*
A room controller, control the game flow of a single game
 */
public class RoomController  extends Thread{

    /*
     *  the ID for this room, this usually used in server side,
     *  to see how many rooms have been created
     */
    final private int roomId;

    /*
     * The App in the server side
     */
    final private App app;

    /**
     * Constructs a room controller object
     *
     * @param roomId is the room id
     * @param app the main server
     */
    public RoomController(int roomId, App app) {
        this.roomId = roomId;
        this.app = app;
    }

    /**
     * room controller runs a loop until the game ends
     */
    @Override
    public void run(){
        Room room = app.getRoomById(roomId);
        try{
            connectPhase(room);
            placementPhase(room);
            while(true){
                attackPhase(room);
                if(room.getStatus().equals("gameOver")){
                    sleep(100);//the game ends, wait clients to exit, then exit
                    break;
                }
            }
        }catch(Exception e){

        }
    }

    /**
     * wait until the room is full, notify the players to go to placement phase
     * @param room is current room
     *
     */
    public void connectPhase(Room room) throws InterruptedException {
        if(!room.getStatus().equals("ConnectPhase")){
            return;
        }
        synchronized (room.getLock()) {
            room.getLock().wait();
        }
        synchronized (room.getPlayerLock()) {//notify the player thread to send message
            room.getPlayerLock().notifyAll();
        }
    }

    /*
     * wait until all placement are finished, then go to the next stage
     * @param room is current room
     */
    public void placementPhase(Room room) throws InterruptedException{
        if(!room.getStatus().equals("doPlacement")){
            return;
        }
        synchronized (room.getLock()) {
            room.getLock().wait();
        }
        synchronized (room.getPlayerLock()) {//notify the player thread to send message
            room.getPlayerLock().notifyAll();
        }
    }

    /*
     * wait until all attacks are finished, then loop until game over
     * @param room is current room
     */
    public void attackPhase(Room room) throws InterruptedException{
        if(!room.getStatus().equals("doAttack")){
            return;
        }
        synchronized (room.getLock()) {
            room.getLock().wait();
        }
        synchronized (room.getPlayerLock()) {//notify the player thread to send message
            room.getPlayerLock().notifyAll();
        }
    }



}
