package edu.duke.group1.server;

import edu.duke.group1.client.App;
import edu.duke.group1.client.AttackController;
import edu.duke.group1.client.Helper;
import edu.duke.group1.client.Player;
import edu.duke.group1.shared.MapForTwo;
import edu.duke.group1.shared.PlayerInfo;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@ExtendWith(ApplicationExtension.class)
public class AttackActionTest {

    App app;
    @Start
    public void start(Stage stage) throws Exception {
        app = new App();

        new Thread(() -> {
            try {
                ServerSocket socket = new ServerSocket(54189);
                Socket clientSocket = socket.accept();
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

                oos.writeObject(null);
                oos.reset();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);
        app.start(stage);
        PlayerInfo info = new PlayerInfo(1);
        AttackController.info = info;
        info.setMap(new MapForTwo());
        info.getMap().addPlayerMapping(1,1);
        App.ps.setScene(AttackController.show(info));
        App.ps.setScene(Helper.getScene("/fxml/attack.fxml"));
        App.ps.show();
        App.player = new Player("127.0.0.1", 54189);

    }

    //@Test
    public void test_submit(FxRobot robot) throws InterruptedException {
        robot.clickOn("#submit");
    }
}
