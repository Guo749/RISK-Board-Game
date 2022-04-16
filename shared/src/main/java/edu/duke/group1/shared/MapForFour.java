package edu.duke.group1.shared;


import java.io.Serializable;
import java.util.List;

public class MapForFour extends AbstractMap implements Serializable {

    @Override
    protected void setUpLookup() {
        this.lookup.put(1, "Beijing");
        this.lookup.put(2, "Shanghai");
        this.lookup.put(3, "Shenzhen");
        this.lookup.put(4, "Guangzhou");
        this.lookup.put(5, "Suzhou");
        this.lookup.put(6, "Hangzhou");
        this.lookup.put(7, "Henan");
        this.lookup.put(8, "Hainan");
    }

    @Override
    protected void setUpPlayerList() {
        for(int i = 1; i <= 4; i++){
            this.playerList.add(i);
        }
    }

    @Override
    protected void setUpRegions() {
        Region Beijing   = new Region(1, "Beijing" ,  List.of(2,8), 1);
        Region Shanghai  = new Region(2, "Shanghai",  List.of(1,3), 1);

        Region Shenzhen  = new Region(3, "Shenzhen",  List.of(2,4), 2);
        Region Guangzhou = new Region(4, "Guangzhou", List.of(3,5), 2);

        Region Suzhou    = new Region(5, "Suzhou",    List.of(4,6), 3);
        Region Hangzhou  = new Region(6, "Hangzhou",  List.of(5,7), 3);

        Region Henan     = new Region(7, "Henan",     List.of(6, 8), 4);
        Region Hainan    = new Region(8, "Hainan",    List.of(1, 7), 4);

        regions.add(Beijing);
        regions.add(Shanghai);
        regions.add(Shenzhen);
        regions.add(Guangzhou);
        regions.add(Suzhou);
        regions.add(Hangzhou);
        regions.add(Henan);
        regions.add(Hainan);
    }
    @Override
    protected void setUpResource(){
        this.playerResource.put(1,new Resource(0,0,1));
        this.playerResource.put(2,new Resource(0,0,1));
        this.playerResource.put(3,new Resource(0,0,1));
        this.playerResource.put(4,new Resource(0,0,1));
    }
    @Override
    protected void setUpSpy(){
        this.playerSpies.put(1, new Spy());
        this.playerSpies.put(2, new Spy());
        this.playerSpies.put(3, new Spy());
        this.playerSpies.put(4, new Spy());
    }
}
