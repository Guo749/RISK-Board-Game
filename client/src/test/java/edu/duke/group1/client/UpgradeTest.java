package edu.duke.group1.client;


import edu.duke.group1.shared.MapForTwo;
import edu.duke.group1.shared.PlayerInfo;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@ExtendWith(ApplicationExtension.class)
public class UpgradeTest {
    edu.duke.group1.client.App app;
    @Start
    public void start(Stage stage) throws Exception {
        app = new edu.duke.group1.client.App();

        new Thread(() -> {
            try {
                ServerSocket socket  = new ServerSocket(54722);
                Socket clientSocket = socket.accept();
                Helper.sendNullAction(clientSocket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);
        app.start(stage);
        PlayerInfo info = new PlayerInfo(1);
        UpgradeController.info = info;
        info.setMap(new MapForTwo());

        info.getMap().addPlayerMapping(1,1);
        edu.duke.group1.client.App.ps.setScene(AttackController.show(info));
        edu.duke.group1.client.App.ps.setScene(Helper.getScene("/fxml/upgrade.fxml"));
        edu.duke.group1.client.App.ps.show();
        Player player = new Player("127.0.0.1", 54722);
        UpgradeController.show(info);

        edu.duke.group1.client.App.player = player;
    }


     //@Test
    public void test_upgrade(FxRobot robot){
        try {
            robot.clickOn("#myCancel");
        }catch (Exception e){}

    }

}

