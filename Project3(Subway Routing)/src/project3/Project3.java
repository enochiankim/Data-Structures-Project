/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Enkim
 */
public class Project3 {

    public static void main(String[] args) {

        //Reads in the mta to file.
        SubwayHtmlReader subwayHtmlReader = new SubwayHtmlReader();

        TrainLines trainLine = new TrainLines();

        //reads from file the Train Line class
        SubwayHtmlReader.readFromFile(trainLine);

        Scanner Keyboard = new Scanner(System.in);

        //First Entry of the Train station
        System.out.println("Please enter the first subway station with correct spelling");
        String station1 = Keyboard.nextLine();

        //Second Entry of the Train Station
        System.out.println("Please enter the second subway station with correct spelling");
        String station2 = Keyboard.nextLine();

        //Sets up the shortest path between two stations.
        ArrayList<String> path = trainLine.ShortestPath(station1, station2);

        //Prints out the shortest path between two stations.
        System.out.println(path);

    }
}

