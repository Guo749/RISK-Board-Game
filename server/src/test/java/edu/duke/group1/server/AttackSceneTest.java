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

@ExtendWith(ApplicationExtension.class)
public class AttackSceneTest {

    App app;
    @Start
    public void start(Stage stage) throws Exception {
        app = new App();

        new Thread(() -> {
            try {
                edu.duke.group1.server.App.main(new String[]{"48159"});
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
        Player player = new Player("127.0.0.1", 48159);
    }

    //@Test
    public void test_Attack_server(FxRobot robot) throws IOException {
        try{
            robot.clickOn("#myCancel");
            System.out.println("f");
        }catch (Exception e){}

    }
}
