package edu.duke.group1.shared;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PlayerInfoTest {
    @Test
    public void test_playerInfo(){
        AbstractMap map = new MapForTwo();
        PlayerInfo pl = new PlayerInfo(12);
        pl.setNeedWait(true);
        pl.setMap(map);
        pl.setCurrRoomId(12);
        pl.setStatus("qw98");
        pl.setAccount("qw");
        Map<Integer,String> map1 = new HashMap<>();
        map1.put(1,"qw");
        pl.setAvailableRooms(map1);
        pl.setLoginMsg("98");
        pl.setOnline(true);
        pl.setPassword("98qw");
        assert(pl.getPlayerId()==12);
        assert(pl.getNeedWait());
        assert(pl.getMap().equals(map));
        assert(pl.getStatus().equals("qw98"));
        assert(pl.getCurrRoomId()==12);
        assert(pl.getAccount().equals("qw"));
        assert(pl.getAvailableRooms().get(1).equals("qw"));
        assert(pl.getOnline()==true);
        assert(pl.getLoginMsg().equals("98"));
        assert(pl.getPassword().equals("98qw"));
    }
}
