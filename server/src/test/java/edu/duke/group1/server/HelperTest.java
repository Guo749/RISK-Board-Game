package edu.duke.group1.server;

import edu.duke.group1.client.Helper;
import edu.duke.group1.client.Player;
import edu.duke.group1.shared.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class HelperTest {
    Stage mainStage;
    @Start
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        new Thread(() -> {
            try {
                App.main(new String[]{"59425"});

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        Thread.sleep(500);

        edu.duke.group1.client.App a = new edu.duke.group1.client.App();

        a.start(stage);

        Player player = new Player("127.0.0.1", 59425);

        edu.duke.group1.client.App.player = player;

    }


   // @Test
    public void test_getScene() throws IOException {
        try{
            Scene scene = Helper.getScene("/fxml/logInPage.fxml");
        }catch (Exception e){}

    }

}

