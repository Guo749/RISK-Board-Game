package edu.duke.group1.server;

import edu.duke.group1.client.App;
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

@ExtendWith(ApplicationExtension.class)
public class LoginTest {


        edu.duke.group1.client.App a;

        @Start
        public void start(Stage stage) throws Exception {
            a = new edu.duke.group1.client.App();

        new Thread(() -> {
            try {
                edu.duke.group1.server.App.main(new String[]{"58465"});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

            a.start(stage);
            Player player = new Player("127.0.0.1", 58465);
            App.player = player;
        }

        /**
         * There is one part to notice, when I make the testing,
         * I usually launch the server in order to have response.
         * @param robot
         */
       //@Test
        public void test_Login(FxRobot robot){
           try{
               robot.doubleClickOn("#userName").write("guoguo");
               robot.doubleClickOn("#password").write("123");


               FxAssert.verifyThat("#userName", TextInputControlMatchers.hasText("guoguo"));
               FxAssert.verifyThat("#password", TextInputControlMatchers.hasText("123"));

               robot.clickOn("#signup");

               robot.clickOn("#login");
           }catch (Exception e){

           }


          //  robot.clickOn("#createBtn");
        }

}
