package edu.duke.group1.server;

import edu.duke.group1.shared.AbstractMap;
import edu.duke.group1.shared.Action;
import edu.duke.group1.shared.ChoosePlayerNumAction;
import edu.duke.group1.shared.EmptyAction;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest5 {



 //   @Test
    public void test_processAction() throws IOException, InterruptedException {
        App app = new App();
        app.addPlayerInfo(0);
        Action action = new ChoosePlayerNumAction(5);
        app.processAction(action, 0);

        assert(app.getPlayerInfo(0).getStatus().equals("doPlacement"));

        Action action2 = new EmptyAction();
//        app.createRoom(0,5);
        app.processAction(action2, 0);
        Action action3 = new Action() {
            @Override
            public void doAction(AbstractMap map) {
                System.out.println("test");
            }

            @Override
            public String getString() {
                return "";
            }
        };

        app.processAction(action3, 0);
    }


}
