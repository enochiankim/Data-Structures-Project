/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
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

    public void flush() { // When we just want to just flush the file but not close it.
        out.flush();
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
