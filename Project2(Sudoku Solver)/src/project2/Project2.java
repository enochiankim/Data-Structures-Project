/*
 * To change this license header, choose License Headers in Project Properties.
*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 *
 * @author Enoch Kim
 */
public class Project2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Makes a SudokuGrid array and takes the .csv file.
        SudokuGrid muhGrid = new SudokuGrid("easy0.csv");
        SudokuGrid muhGrid2 = new SudokuGrid("easy1.csv");

        // Writes the .csv file into a text file
        muhGrid.writeToFile("Test0.txt");
        muhGrid2.writeToFile("Test1.txt");
        
        // Calls the solvesSudokuGrid method and solves the SudokuGrid.
        muhGrid.solveSudokuGrid();
        muhGrid2.solveSudokuGrid();
        
        //Prints the SudokuGrid.
        System.out.println("\n");
        System.out.println("Easy 0 Solution:" + "\n");
        muhGrid.printSudokuGrid();
        System.out.println("\n");
        System.out.println("Easy 1 Solution:" + "\n");
        muhGrid2.printSudokuGrid();
        
        // Prints the Solved SudokuGrid into the text file called "resultEasy0" and "resultEasy1"
        muhGrid.writeToFile("resultEasy0.txt");
        muhGrid2.writeToFile("resultEasy1.txt");

    }

}













































