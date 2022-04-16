package edu.duke.group1.shared;


import java.io.Serializable;
import java.util.List;

public class MapForThree extends AbstractMap implements Serializable {
    public MapForThree(){
        super();
    }

    @Override
    protected void setUpLookup() {
        this.lookup.put(1, "Beijing");
        this.lookup.put(2, "Shanghai");
        this.lookup.put(3, "Guangzhou");
        this.lookup.put(4, "Hangzhou");
        this.lookup.put(5, "Yangzhou");
        this.lookup.put(6, "Suzhou");
        this.lookup.put(7, "Shanxi");
        this.lookup.put(8, "Henan");
        this.lookup.put(9, "Hebei");
    }

    @Override
    protected void setUpPlayerList() {
        for(int i = 1; i <= 3; i++){
            this.playerList.add(i);
        }
    }

    @Override
    protected void setUpRegions() {
        Region Beijing   = new Region(1, "Beijing",   List.of(2,3,4), 1);
        Region Shanghai  = new Region(2, "Shanghai",  List.of(1,3,8), 1);
        Region Guangzhou = new Region(3, "Guangzhou", List.of(1,2,5,7), 1);

        Region Hangzhou = new Region(4, "Hangzhou",   List.of(1,5,6), 2);
        Region Yangzhou = new Region(5, "Yangzhou",   List.of(3,4,6,7), 2);
        Region Suzhou   = new Region(6, "Suzhou",     List.of(4,5,9), 2);

        Region Shanxi   = new Region(7, "Shanxi",     List.of(3,5,8,9), 3);
        Region Henan    = new Region(8, "Henan",      List.of(2,7,9),   3);
        Region Hebei    = new Region(9, "Hebei",      List.of(6,7,8), 3);

        regions.add(Beijing);
        regions.add(Shanghai);
        regions.add(Guangzhou);
        regions.add(Hangzhou);
        regions.add(Yangzhou);
        regions.add(Suzhou);
        regions.add(Shanxi);
        regions.add(Henan);
        regions.add(Hebei);

    }
    @Override
    protected void setUpResource(){
        this.playerResource.put(1,new Resource(0,0,1));
        this.playerResource.put(2,new Resource(0,0,1));
        this.playerResource.put(3,new Resource(0,0,1));
    }
    @Override
    protected void setUpSpy(){
        this.playerSpies.put(1, new Spy());
        this.playerSpies.put(2, new Spy());
        this.playerSpies.put(3, new Spy());
    }
}
