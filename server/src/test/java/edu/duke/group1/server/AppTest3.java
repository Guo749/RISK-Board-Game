package edu.duke.group1.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest3 {

   // @Test
    public void test_getPLayerInfo() throws IOException {
        App app = new App();

        app.addPlayerInfo(0);
        app.addPlayerInfo(1);
        app.addPlayerInfo(2);
        app.addPlayerInfo(3);
        app.addPlayerInfo(4);

        assert(app.getPlayerInfo(2) != null);
        assert(app.getPlayerInfo(0).getStatus().equals("choosePlayerNum"));

    }



}
