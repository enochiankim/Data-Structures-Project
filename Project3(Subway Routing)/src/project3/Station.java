/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.util.HashMap;
import java.util.Map;

/**
 * The station class, sets up the vertices for every station and the connection for the station, 
 * it basically makes the path for the station.
 *
 * @author Enkim
 */
public class Station {

    String name;

    public String getName() {
        return name;
    }
    Map<String, Station> neighboring;

    public Map<String, Station> getNeighboring() {
        return neighboring;
    }

    Station(String s) {
        name = s;
        neighboring = new HashMap<>();
    }

    //connects the station 
    public void AddNeighbor(Station s) {
        if (!neighboring.containsKey(s.getName())) {
            neighboring.put(s.getName(), s);
        }

        s.addConnection(this);
    }

    //connects the station.
    public void addConnection(Station s) {
        if (!neighboring.containsKey(s.getName())) {
            neighboring.put(s.getName(), s);
        }
    }

    //Compares the station names.
    public Boolean compare(Station o) {
        if (this.name.equals(o.getName())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
