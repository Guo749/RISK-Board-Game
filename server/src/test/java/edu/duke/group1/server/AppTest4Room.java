package edu.duke.group1.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest4Room {

    //@Test
    public void test_Room() throws IOException {
        App app = new App();
        assert(app.getPlayerInfoSize() == 0);
        app.addPlayerInfo(0);
        app.addPlayerInfo(1);
        app.addPlayerInfo(2);
        app.addPlayerInfo(3);
        app.addPlayerInfo(4);
        assert(app.getPlayerInfoSize() == 5);

        app.createRoom(0, 5);
        Room room = app.getRoomById(0);
        app.joinRoom(1, room.getRoomId());
        app.joinRoom(2, room.getRoomId());
        app.joinRoom(3, room.getRoomId());
        app.joinRoom(4, room.getRoomId());


        Room room1 = app.getRoomById(566);
        assert(room1 == null);


    }

     
}
