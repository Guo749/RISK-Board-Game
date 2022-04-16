package edu.duke.group1.server;

import edu.duke.group1.shared.*;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ActionTest {
    //@Test
    public void test_ProcessAction() throws IOException {
        App app = new App();

        Action action1 = new RegisterAction("guoguo", "123");
        app.processActionOutOfRoom(action1);
        app.processActionOutOfRoom(action1);

        Action action2 = new LoginAction("guoguo", "12");
        Action action3 = new LoginAction("guoguo", "123");

        app.processActionOutOfRoom(action2);
        app.processActionOutOfRoom(action3);

        app.processActionOutOfRoom(null);
    }


   // @Test
    public void test_processAction() throws IOException, InterruptedException {
        App app = new App();
        Action action = new RegisterAction("guoguo", "123");
        app.processActionOutOfRoom(action);

        app.canLogin("guoguo", "1231231");
        app.canLogin("guoguo", "1");


        Action action1 = new ChoosePlayerNumAction(2);
        app.processAction(action1, 0);

        Action action2 = new RegisterAction("faff", "334");
        Action action25 = new LoginAction("faff", "334");
        Action action3 = new RegisterAction("afeafa", "334");
        app.processActionOutOfRoom(action2);
        app.processActionOutOfRoom(action3);

        Action action21 = new JoinRoomAction(0);
        Action action31 = new JoinRoomAction(0);
        app.processAction(action21, 1);
        app.processAction(action21, 1);
        app.processAction(action31, 2);


        Room room = app.getRoomById(0);
        room.setPlayerMapping();
        room.setUnplacedUnitsMapping();

        Action action4 = new Place(1, "Beijing", 15, 0);
        Action action5 = new Place(2, "Shanghai", 15, 0);
        app.processAction(action4, 0);
        app.processAction(action5, 0);
        app.processAction(action5, 1);


        Action action6 = new Attack(1, "Beijing", "Shanghai", 1, 0);
        app.processAction(action6, 1);

        Action action7 = new Finish();
        app.processAction(action7, 0);
        app.processAction(action7, 1);


        Action action8 = new Attack(1, "Beijing", "Suzhou", 1, 0);
        app.processAction(action8, 1);


        Action action9 = new Move(1, "Beijing", "Suzhou", 3, 0);
        app.processAction(action9, 1);


        Action action10 = new UpgradeTechAction(1);
        app.processAction(action10, 1);

        Action action11 = new UpgradeAction(1, "Beijing", 1, 0, 1);
        app.processAction(action11, 1);

        Action action12 = new LoginAction("guoguo", "123");
        app.processAction(action12, 1);

        app.processAction(action7, 0);
        app.processAction(action7, 1);

        room.setStatus("doPlacement");
        app.processAction(action21, 1) ;
        app.processAction(action31, 2);
        room.setStatus("doAttack");
        app.processAction(action21, 1) ;
        app.processAction(action31, 2);
        app.processAction(action1, 2);
        app.processAction(action25, 1) ;
    }


}
