package edu.duke.group1.shared;

import java.io.Serializable;
import java.util.HashMap;

public class Spy implements Serializable {

    private HashMap<String,Integer> spyLocation;

    public Spy(){
        spyLocation = new HashMap<>();
    }

    public void addSpy(String regionName,int num){

        if(spyLocation.get(regionName)==null){
            spyLocation.put(regionName,num);
        }
        else{
            Integer n = spyLocation.get(regionName);
            spyLocation.put(regionName,n+num);
        }
    }
    public boolean removeSpy(String regionName,int num){
        if(spyLocation.get(regionName)==null) return false;
        int res = spyLocation.get(regionName) - num;
        if(res < 0) return false;
        spyLocation.put(regionName,res);
        if(res == 0)
            spyLocation.remove(regionName);
        return true;
    }


    public HashMap<String, Integer> getSpyLocation() {
        return spyLocation;
    }
}
