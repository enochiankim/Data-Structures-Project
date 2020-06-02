/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * This class adds in the train lines that are being read from the mta site and adds it into a hashmap and has the function of finding the shortest path along the mta map.
 *
 * @author Enkim
 */
public class TrainLines {

    //The dictionay of arrays of stations
    Map<String, String[]> lines;
    //All of the Stations 
    Map<String, Station> stations;

    public TrainLines() {
        lines = new HashMap<>();
        stations = new HashMap<>();
    }

    public void addLine(String name, String[] stationsinfo) {
        //Adds the lines
        lines.put(name, stationsinfo);

        Station currentStation;

        //If the station is not in the map, add it.
        if (!stations.containsKey(stationsinfo[0])) {

            // Makes the intial station
            currentStation = new Station(stationsinfo[0]);
            //Puts it into the list of the station.
            stations.put(stationsinfo[0], currentStation);
        } //Updates
        else {
            currentStation = stations.get(stationsinfo[0]);
        }

        //iteriates through the station lines.
        for (int i = 1; i < stationsinfo.length; i++) {
            String s = stationsinfo[i];
            Station preStation = currentStation;

            //Adds and finds the station along the line.
            if (!stations.containsKey(s)) {
                currentStation = new Station(s);
                stations.put(stationsinfo[i], currentStation);

            } //Updates
            else {
                currentStation = stations.get(s);
            }

            //connects the currtent and the preivous station.
            currentStation.AddNeighbor(preStation);
        }
    }

    public ArrayList<String> ShortestPath(String startStation, String endStation) {
        //checking if the start and end exist. 
        ArrayList<String> returnList = new ArrayList<>();
        Station currentStation = stations.get(startStation);
        TreeNode rootNode = new TreeNode(startStation, null);
        HashSet<String> visitedStation = new HashSet<>();
        ArrayList<TreeNode> nextToVisit = new ArrayList<>();
        ArrayList<TreeNode> nextLevel = new ArrayList<>();
        TreeNode currentNode = rootNode;
        visitedStation.add(startStation);
        nextToVisit.add(currentNode);

        Boolean check = true;
        while (check) {
            //iterate through currentLevel.
            for (int i = 0; i < nextToVisit.size(); i++) {
                currentNode = nextToVisit.get(i);
                currentStation = stations.get(currentNode.getStation());
                if (currentStation.getName().equals(endStation)) {
                    break;
                }
                //add to next to the nextLevel to be visited
                Map<String, Station> temp = currentStation.getNeighboring();

                for (Station station : temp.values()) {
                    if (!visitedStation.contains(station.getName())) {
                        visitedStation.add(station.getName());
                        nextLevel.add(currentNode.addChild(station.getName()));

                    }
                }
            }
            if (currentStation.getName().equals(endStation)) {
                break;
            }
            //move down next Level
            nextToVisit = nextLevel;
            nextLevel = new ArrayList<>();
        }

        //back track up the tree getting the Name of each Station
        while (currentNode != null) {
            returnList.add(0, currentNode.getStation());
            currentNode = currentNode.getParent();
        }
        return returnList;

    }

}

