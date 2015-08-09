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

//a class to test the pHash algorithm

public class Testphash {
    public static void main(String args[]) throws FileNotFoundException, Exception{
        ImagePHash obj = new ImagePHash();
        
        
        InputStream image1 = new FileInputStream("./src/resources/images/1000.jpg");
        InputStream image2 = new FileInputStream("./src/resources/images/1000.jpg");
        
        String hash1 = obj.getHash(image1);
        String hash2 = obj.getHash(image2);
        
        System.out.println(hash1+" \n "+hash2);
        
        System.out.println("hamming distance: "+obj.distance(hash1, hash2));
    }
}
