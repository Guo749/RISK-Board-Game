package edu.duke.group1.server;

import edu.duke.group1.client.MoveController;
import edu.duke.group1.client.PlaceController;
import edu.duke.group1.client.PlayController;
import edu.duke.group1.client.Player;
import edu.duke.group1.shared.MapForTwo;
import edu.duke.group1.shared.Place;
import edu.duke.group1.shared.PlayerInfo;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.TextInputControlMatchers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


@ExtendWith(ApplicationExtension.class)
public class PlayTest {
    edu.duke.group1.client.App a;
    ServerSocket socket;

    @Start
    public void start(Stage stage) throws Exception {
        new Thread(() -> {
            try {
                //edu.duke.group1.server.App.main(new String[]{});
                socket = new ServerSocket(54321);
                Socket client = socket.accept();

                ObjectInputStream ois  = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

                for(int i = 0; i < 1; i++) {
                    oos.writeObject(null);
                    oos.reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        a = new edu.duke.group1.client.App();

        PlayerInfo info = new PlayerInfo(1);
        info.setMap(new MapForTwo());
        info.getMap().addPlayerMapping(1,1);
        Room room = new Room(1,2);
        room.addPlayerId(1);
        room.addPlayerId(2);

        room.setPlayerMapping();
        stage.setScene(PlayController.show(info));
        stage.show();

        Player player = new Player("127.0.0.1", 54321);
        edu.duke.group1.client.App.player = player;
    }


//    @Test
//    public void test_moveBtn(FxRobot robot){
//        robot.clickOn("#move");
//
//    }

    //@Test
    public void test_switch(FxRobot robot) throws IOException {
        try{

        }catch(Exception e){
            PlayController.robot = true;
            robot.clickOn("#switchRoom");
        }

       // socket.close();
    }
//
//    @Test
//    public void test_attack(FxRobot robot){
//        robot.clickOn("#upgrade");
//    }
//
//    @Test
//    public void test_finish(FxRobot robot){
//        robot.clickOn("#finish");
//    }

}
