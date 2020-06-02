package project2;



/**
 * Retrieved this code from https://repl.it/@Enkim2018/File-Writer-Demo, the instructor provided the code for this class.
 * This FileOutput class, has four .txt for this project. The first two are called Test0.txt and Test1.txt which prints the unsolved SudokuGrid and the 
 * last two are called resultEasy0.txt and resultEasy1.txt which prints the solved SudokuGrid.
 * 
 * @author Enoch Kim
 */
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileOutput {

    String fileName;
    PrintWriter out;

    public FileOutput(String fileName) {

        try {
            out = new PrintWriter(new FileWriter(fileName));
            this.fileName = fileName;
        } catch (IOException e) {
            System.out.println("Failed to open file " + fileName);
        }
    }

    public void writeLine(String line) {
        try {
            out.println(line);
        } catch (NullPointerException np) {
            System.out.println("Must first initiate the file writer");
        }
    }

    public void close() {
        try {
            out.flush(); //In case something got stuck in the buffer
            out.close(); //Properlly close the file and release control
        } catch (NullPointerException np) {
            System.out.println("Must first initiate the file writer");

        }
    }
}









