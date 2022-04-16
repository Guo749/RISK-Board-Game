package edu.duke.group1.shared;


import java.io.Serializable;
import java.util.List;

public class MapForTwo extends AbstractMap implements Serializable {
    public MapForTwo() {
        super();
    }

    @Override
    protected void setUpLookup() {
        this.lookup.put(1, "Beijing");
        this.lookup.put(2, "Chengdu");
        this.lookup.put(3, "Suzhou");
        this.lookup.put(4, "Kunming");
        this.lookup.put(5, "Guangzhou");
        this.lookup.put(6, "Shanghai");
    }

    @Override
    protected void setUpPlayerList() {
        this.playerList.add(1);
        this.playerList.add(2);
    }

    @Override
    protected void setUpRegions() {
        Region Beijing = new Region(1, "Beijing", List.of(2, 6),1);
        Region Chengdu = new Region(2, "Chengdu", List.of(1, 3, 5),1);
        Region Suzhou  = new Region(3, "Suzhou", List.of(2, 4),1);

        Region Shanghai  = new Region(6, "Shanghai" , List.of(1, 5),2);
        Region Guangzhou = new Region(5, "Guangzhou", List.of(2, 4, 6),2);
        Region Kunming   = new Region(4, "Kunming"  , List.of(3, 5),2);

        regions.add(Beijing);
        regions.add(Chengdu);
        regions.add(Suzhou);
        regions.add(Kunming);
        regions.add(Guangzhou);
        regions.add(Shanghai);
    }
    @Override
    protected void setUpResource(){
        this.playerResource.put(1,new Resource(0,0,1));
        this.playerResource.put(2,new Resource(0,0,1));
    }
    @Override
    protected void setUpSpy(){
        this.playerSpies.put(1, new Spy());
        this.playerSpies.put(2, new Spy());
    }

}
