package edu.duke.group1.server;

import edu.duke.group1.client.Player;
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
import java.net.ServerSocket;
import java.net.Socket;

@ExtendWith(ApplicationExtension.class)
public class UITest {
        edu.duke.group1.client.App a;
        ServerSocket socket;
        @Start
        public void start(Stage stage) throws Exception {
            new Thread(() -> {
                try {
                    App app = new App();
                    app.main(new String[]{"51303"});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            a = new edu.duke.group1.client.App();
            a.start(stage);
            Player player = new Player("127.0.0.1", 51303);
            edu.duke.group1.client.App.player = player;
        }

        /**
         * There is one part to notice, when I make the testing,
         * I usually launch the server in order to have response.
         *
         * @param robot
         */
        //@Test
        public void test_Login(FxRobot robot) throws IOException {
            try{
                robot.doubleClickOn("#userName").write("guoguo");
                robot.doubleClickOn("#password").write("123");


                FxAssert.verifyThat("#userName", TextInputControlMatchers.hasText("guoguo"));
                FxAssert.verifyThat("#password", TextInputControlMatchers.hasText("123"));

                robot.clickOn("#signup");

                robot.clickOn("#login");
                //  socket.close();
            }catch (Exception e){

            }


           // robot.clickOn("#createBtn");
        }


}
