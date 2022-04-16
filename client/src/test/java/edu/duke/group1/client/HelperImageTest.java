package edu.duke.group1.client;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.net.ServerSocket;

@ExtendWith(ApplicationExtension.class)
public class HelperImageTest {
    Stage mainStage;
    @Start
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        new Thread(() -> {
            try {
                ServerSocket socket = new ServerSocket(51749);
                socket.accept();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        Thread.sleep(500);

        edu.duke.group1.client.App a = new edu.duke.group1.client.App();

        a.start(stage);


    }

    //@Test
    public void test_IniIMG_client(){

        try{
            Helper.initializeMap(2);
            Helper.initializeMap(3);
            Helper.initializeMap(4);
            Helper.initializeMap(5);
        }catch (Exception e){

        }

    }
}

