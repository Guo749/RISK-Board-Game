package edu.duke.group1.server;

import edu.duke.group1.client.App;
import edu.duke.group1.client.Helper;
import edu.duke.group1.client.Player;
import edu.duke.group1.shared.Action;
import edu.duke.group1.shared.EmptyAction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HelperSendActionTest {
    //@Test
    public void test_SendAction() throws Exception {

        try{
            new Thread(() -> {
                try {
                    ServerSocket socket = new ServerSocket(59463);
                    Socket clientSocket = socket.accept();
                    Helper.sendNullAction(clientSocket);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();

            Thread.sleep(1000);
            System.out.println("222");
            Player player = new Player("127.0.0.1",59463);
            System.out.println("333");
            App.player = player;
            Action action = new EmptyAction();
            System.out.println("111");
            Helper.sendAction(action);
        }catch (Exception e){}


    }
}
