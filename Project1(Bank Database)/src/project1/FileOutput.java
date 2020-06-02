/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

/**
 * Retrieved this code from https://repl.it/@Enkim2018/File-Writer-Demo, the instructor provided the code for this class.
 * This FileOutput class, has two .txt for this project. the first one is the outputs.txt and the second one is the Log.txt.
 * The output.txt file provides a text file which contains of list of each and every performed. The Log file is where it states the final state 
 * of the accounts, such as the amount left in the checking, how much was paid for the credit bill and the clients name and ssn.
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



