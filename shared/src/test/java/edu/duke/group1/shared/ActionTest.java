package edu.duke.group1.shared;

import org.junit.jupiter.api.Test;

/**
 * This test is used for test other actions other than attack, place, move
 */
public class ActionTest {
    @Test
    public void test_ChoosePlayerNumAction(){
        AbstractMap map = new MapForTwo();
        ChoosePlayerNumAction cpn = new ChoosePlayerNumAction(5);
        cpn.doAction(map);
        assert (cpn.getPlayerNum()==5);
    }
    @Test
    public void test_EmptyAction(){
        AbstractMap map = new MapForTwo();
      EmptyAction ea = new EmptyAction();
      ea.doAction(map);
    }
    @Test
    public void test_finish(){
        AbstractMap map = new MapForTwo();
        Finish fi = new Finish();
        fi.doAction(map);
        fi.getString();
    }
    @Test
    public void test_joinRoom(){
        AbstractMap map = new MapForTwo();
        JoinRoomAction j = new JoinRoomAction(5);
        j.doAction(map);
        assert(j.getRoomId()==5);
        j.getString();
    }
    @Test
    public void test_login(){
        AbstractMap map = new MapForTwo();
        LoginAction l =new LoginAction("qw98","qw98");
        l.doAction(map);
        assert(l.getAccount().equals("qw98"));
        assert(l.getPassword().equals("qw98"));
        l.getString();
    }
    @Test
    public void test_RegisterAction(){
        AbstractMap map = new MapForTwo();
        RegisterAction r = new RegisterAction("qw98","qw98");
        r.doAction(map);
        assert(r.getAccount().equals("qw98"));
        assert(r.getPassword().equals("qw98"));
        r.getString();
    }


}
