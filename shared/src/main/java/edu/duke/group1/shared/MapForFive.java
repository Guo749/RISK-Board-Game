package edu.duke.group1.shared;


import java.io.Serializable;
import java.util.List;

public class MapForFive extends AbstractMap implements Serializable {

    @Override
    protected void setUpLookup() {
        this.lookup.put(1, "Beijing");
        this.lookup.put(2, "Shanghai");

        this.lookup.put(3, "Guangzhou");
        this.lookup.put(4, "Shenzhen");

        this.lookup.put(5, "Henan");
        this.lookup.put(6, "Tibet");

        this.lookup.put(7, "Suzhou");
        this.lookup.put(8, "Hangzhou");

        this.lookup.put(9, "Qingdao");
        this.lookup.put(10, "Dalian");
    }

    @Override
    protected void setUpPlayerList() {
        for (int i = 1; i <= 5; i++) {
            this.playerList.add(1);
        }
    }

    @Override
    protected void setUpRegions() {
        Region  Beijing   = new Region(1, "Beijing",   List.of(2,8), 1);
        Region  Shanghai  = new Region(2, "Shanghai",  List.of(1,3), 1);
        Region  Guangzhou = new Region(3, "Guangzhou", List.of(2,4), 2);
        Region  Shenzhen  = new Region(4, "Shenzhen",  List.of(3,9), 2);
        Region  Henan     = new Region(5, "Henan",     List.of(6,10), 4);
        Region  Tibet     = new Region(6, "Tibet",     List.of(5,7), 4);
        Region  Suzhou    = new Region(7, "Suzhou",    List.of(6,8), 5);
        Region  Hangzhou  = new Region(8, "Hangzhou",  List.of(1,7), 5);
        Region  Qingdao   = new Region(9, "Qingdao",   List.of(4,10), 3);
        Region  Dalian    = new Region(10, "Dalian",   List.of(5,9), 3);

        regions.add( Beijing  );
        regions.add( Shanghai );
        regions.add( Guangzhou);
        regions.add( Shenzhen );
        regions.add( Henan    );
        regions.add( Tibet    );
        regions.add( Suzhou   );
        regions.add( Hangzhou );
        regions.add( Qingdao  );
        regions.add( Dalian   );

    }
    @Override
    protected void setUpResource(){
        this.playerResource.put(1,new Resource(0,0,1));
        this.playerResource.put(2,new Resource(0,0,1));
        this.playerResource.put(3,new Resource(0,0,1));
        this.playerResource.put(4,new Resource(0,0,1));
        this.playerResource.put(5,new Resource(0,0,1));
    }
    @Override
    protected void setUpSpy(){
        this.playerSpies.put(1, new Spy());
        this.playerSpies.put(2, new Spy());
        this.playerSpies.put(3, new Spy());
        this.playerSpies.put(4, new Spy());
        this.playerSpies.put(5, new Spy());
    }
}
