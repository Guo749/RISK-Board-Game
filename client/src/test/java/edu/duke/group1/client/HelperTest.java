package edu.duke.group1.client;


import edu.duke.group1.shared.AbstractMap;
import edu.duke.group1.shared.MapForTwo;
import edu.duke.group1.shared.PlayerInfo;
import edu.duke.group1.shared.Region;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@ExtendWith(ApplicationExtension.class)
public class HelperTest {
    Stage mainStage;
    @Start
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        new Thread(() -> {
            try {
                ServerSocket socket = new ServerSocket( 54127);
                Socket clientSocket = socket.accept();
                Helper.sendNullAction(clientSocket);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        Thread.sleep(500);

        edu.duke.group1.client.App a = new edu.duke.group1.client.App();

        a.start(stage);

        Player player = new Player("127.0.0.1", 54127);

        edu.duke.group1.client.App.player = player;

    }


    //@Test
    public void test_getScene() throws IOException {
        Scene scene = Helper.getScene("/fxml/logInPage.fxml");
    }

   // @Test
//    public void test_printRegionInfo(){
//        AbstractMap map = new MapForTwo();
//        TextView view = new TextView(map);
//        PlayerInfo info = new PlayerInfo(1);
//        info.setMap(map);
//        map.addPlayerMapping(1,1);
//
//        String beijing_ = view.printRegionInfo("Beijing ", info);
//
//        System.out.println(beijing_);
//    }
}

