package edu.duke.group1.shared;



/**
 * This class is the action to join a room
 * roomid is the room the player want to join
 */
public class JoinRoomAction extends Action {
    private int roomId;

    public JoinRoomAction(int roomId){
        this.roomId = roomId;
    }

    @Override
    public void doAction(AbstractMap map) { }

    public int getRoomId() {
        return roomId;
    }

    @Override
    public String getString(){
        return "";
    }
}