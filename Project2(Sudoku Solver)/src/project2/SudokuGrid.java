/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Enoch Kim
 */
public class SudokuGrid {

    int[][] SudokuGrid;
    int[][] OrginalSudokuGrid;

    // HashMap stores the candidates of the cell. and the String is the (X,Y). 
    //ArrayList is the candidates aka be the possibly numbers of the candidates in a particular cell. for ex (0,0 can be 3 4 5)
    HashMap<String, ArrayList<Integer>> candidateForCells;

    private SudokuGrid() {
        SudokuGrid = new int[9][9];
        OrginalSudokuGrid = new int[9][9];
        candidateForCells = new HashMap<>();
    }

    public SudokuGrid(String Filename) {
        this();
        //Parsing the input file.
        TextFileInput input = new TextFileInput(Filename);
        String line = input.readLine();
        int lineNumber = 0;
        while (line != null) {

            // Gets the indexes of all commas.
            int comma = 0;
            int[] commaIndex = new int[8];
            int count = 0;
            while (count != 8) {
                comma = line.indexOf(',', comma);
                commaIndex[count++] = comma++; // aka .next
            }

            if (Character.isDigit(line.charAt(0))) {
                int x = lineNumber;
                // Taking the first char, convert it to String, then convert String to Integer.
                SudokuGrid[x][0] = Integer.parseInt(String.valueOf(line.charAt(0)));
                OrginalSudokuGrid[x][0] = Integer.parseInt(String.valueOf(line.charAt(0)));
            }

            for (int i = 0; i < commaIndex.length; i++) {
                try {
                    if (Character.isDigit(line.charAt(commaIndex[i] + 1))) {
                        SudokuGrid[lineNumber][i + 1] = Integer.parseInt(String.valueOf(line.charAt(commaIndex[i] + 1)));
                        OrginalSudokuGrid[lineNumber][i + 1] = Integer.parseInt(String.valueOf(line.charAt(commaIndex[i] + 1)));
                    }
                } catch (Exception e) {
                }
            }
                // Creating an empty ArrayList for the candidates for every cell, also to avoid null pointer exception.
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    candidateForCells.put(stringGen(i, j), new ArrayList());
                }
            }

            lineNumber++;
            // Reads the line aka the CSV File.
            line = input.readLine();
        }
        // Fills the cell the with the available candidates
        fillCellGrid();
    }
    
    // Takes in two values and converts in a string, for ex. (0,0),(0,1). Used mostly for the hashmap.
    private String stringGen(int val1, int val2) {
        return String.valueOf(val1) + String.valueOf(val2);
    }

    // Fills in the Cell Numbers of the Grid.
    public void fillCellGrid() {
        for (int i = 0; i < SudokuGrid.length; i++) {
            for (int j = 0; j < SudokuGrid.length; j++) {
                if (OrginalSudokuGrid[i][j] == 0) {
                    //checks the candidates that can go into cell i,j
                    for (int c = 1; c < 10; c++) {
                        //checks if the candidates is valid, if it is vaild, it is added to the cell candidates list.
                        if (checkBlockSudokuGrid(i, j, c) && checkHorizontalSudokuGrid(i, j, c)
                                && checkVerticalSudokuGrid(i, j, c)) {
                            //Gets the arrayList and adds in another candidated.
                            candidateForCells.get(stringGen(i, j)).add(c);
                        }
                    }
                }
            }
        }
    }

    // Prints SudokuGrid
    public void printSudokuGrid() {
        for (int i = 0; i < SudokuGrid.length; i++) {
            for (int j = 0; j < SudokuGrid.length; j++) {
                System.out.print(SudokuGrid[i][j]);
                
            }
            System.out.println();
        }
    }

    // Checks if the given number is in the Horizontal.
    public boolean checkHorizontalSudokuGrid(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (num == SudokuGrid[row][i]) {
                return false;
            }
        }
        return true;
    }

    // Checks if the given number is in the Vertical.
    public boolean checkVerticalSudokuGrid(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (num == SudokuGrid[i][col]) {
                return false;
            }
        }
        return true;
    }

    //Checks if the given number is in the 3 x 3 block.
    public boolean checkBlockSudokuGrid(int row, int col, int num) {
        int blockX = 0;
        int blockY = 0;

        while (blockX + 3 <= row) {
            blockX += 3;
        }

        while (blockY + 3 <= col) {
            blockY += 3;
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (num == SudokuGrid[i + blockX][j + blockY]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Write the SudokuGrid to a File name.
    public void writeToFile(String Filename) {
        FileOutput output = new FileOutput(Filename);
        for (int i = 0; i < SudokuGrid.length; i++) {
            String temp = "";
            for (int j = 0; j < SudokuGrid.length; j++) {
                temp += String.valueOf(SudokuGrid[i][j]);
            }
            output.writeLine(temp);
        }
        output.close();
    }
    
    // Checks for as long you have candidates in the cell.
    private boolean complete() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (candidateForCells.get(stringGen(row, col)).size() > 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void solveSudokuGrid() {
        while (!complete()) {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    // if there is only one candidated place it into the cell
                    if (candidateForCells.get(stringGen(row, col)).size() == 1) {
                        int placedNum = (int) candidateForCells.get(stringGen(row, col)).get(0);
                        SudokuGrid[row][col] = placedNum;
                        //"backtracks" Row, Column, and 3x3 grid, removing the integer. 
                        update(row, col, placedNum);
                    }
                }
            }
        }
    }

    private void update(int row, int col, Integer num) {
        // Goes through every row, and removes the integer if its there.
        for (int x = 0; x < 9; x++) {
            candidateForCells.get(stringGen(x, col)).remove(num);
            // Goes through every column and removes the integer if its there. 
            for (int y = 0; y < 9; y++) {
                candidateForCells.get(stringGen(row, y)).remove(num);
            }

        }
        // Goes through every 3x3 Grid and removes the integer if its there.
        int blockX = 0;
        int blockY = 0;

        while (blockX + 3 <= row) {
            blockX += 3;
        }

        while (blockY + 3 <= col) {
            blockY += 3;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                candidateForCells.get(stringGen(i + blockX, j + blockY)).remove(num);
            }
        }

    }

}










