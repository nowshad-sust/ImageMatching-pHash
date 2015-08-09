/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author nowshad
 */

public class FileAppend {
    public void appendFile(String textToAppend, String filePath){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("myfile.txt", true)));
            out.println("the text");
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
    
    public static void main(String args[]){
        FileAppend fa = new FileAppend();
        //fa.appendFile("","");
    }
}
