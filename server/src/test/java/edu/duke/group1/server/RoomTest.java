package edu.duke.group1.server;

import edu.duke.group1.shared.*;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RoomTest {

   // @Test
    public void test_roomConstructor(){
        Room room = new Room(1, 3);

        Room room2 = new Room(2, 4);
        Room room3 = new Room(3, 5);
        Room room4 = new Room(4, 2);
        Room room5 = new Room(4, 100);
        assert (room5.getMap() == null);
        assert(room.getRoomId() == 1);

        room.addPlayerId(1);
        room.addPlayerId(2);
        assert (!room.isFull());
        room.addPlayerId(3);

        assert (room.getPlayerIds().size() == 3);

        assert (room.isFull());

        assert(room.getMap().getClass() == MapForThree.class);

        assert(room2.getMap().getClass() == MapForFour.class);
        assert(room3.getMap().getClass() == MapForFive.class);
        assert(room4.getMap().getClass() == MapForTwo.class);


        room.setPlayerMapping();

        room.addToMoves(new Move(1, "beijing", "shanghai", 15, 1));

        ArrayList<Move> moves = new ArrayList<>();
        room.setMoves(moves);
        assert(room.getMoves() == moves);

        ArrayList<Attack> attacks =  new ArrayList<>();
        room.setAttacks(attacks);
        assert(room.getAttacks() == attacks);
        room.setStatus("fuck");
        assert(room.getStatus().equals("fuck"));

       // room.addToAttacks(new Attack(1, "beijing", "shanghai", 14));
    }



}
