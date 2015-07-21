/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author nowshad
 */

public class Testphash {
    public static void main(String args[]) throws FileNotFoundException, Exception{
        ImagePHash obj = new ImagePHash();
        
        
        InputStream image = new FileInputStream("./src/test/source.jpg");
        
        String hash = obj.getHash(image);
        
        System.out.println("hash: " + hash);
    }
}
