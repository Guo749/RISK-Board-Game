package edu.duke.group1.client;


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
public class LoginTest {


    edu.duke.group1.client.App a;

    @Start
    public void start(Stage stage) throws Exception {
        a = new edu.duke.group1.client.App();

        new Thread(() -> {
            try {
                ServerSocket socket = new ServerSocket(58477);
                Socket clientSocket = socket.accept();
                Helper.sendNullAction(clientSocket);

                //Thread.sleep(1000);

                //Helper.sendNullAction(clientSocket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        a.start(stage);
        Player player = new Player("127.0.0.1", 58477);
        App.player = player;
    }

    /**
     * There is one part to notice, when I make the testing,
     * I usually launch the server in order to have response.
     * @param robot
     */
   // @Test
    public void test_Login(FxRobot robot){
        try{
            robot.doubleClickOn("#userName").write("guoguo");
            robot.doubleClickOn("#password").write("123");


            FxAssert.verifyThat("#userName", TextInputControlMatchers.hasText("guoguo"));
            FxAssert.verifyThat("#password", TextInputControlMatchers.hasText("123"));

            //robot.clickOn("#signup");

            robot.clickOn("#login");

            //  robot.clickOn("#createBtn");
        }catch(Exception e){

        }

    }

}

