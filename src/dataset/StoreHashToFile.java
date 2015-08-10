/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataset;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author nowshad
 */
public class StoreHashToFile {
    public static void main(String args[]) throws FileNotFoundException, Exception{
        
        ImagePHash obj = new ImagePHash();
        
        
        InputStream image1 = new FileInputStream(".\\resources\\images\\source.jpg");
        InputStream image2 = new FileInputStream(".\\resources\\images\\source.jpg");
        
        String hash1 = obj.getHash(image1);
        String hash2 = obj.getHash(image2);
        
        System.out.println(hash1+" \n "+hash2);
        
        System.out.println("hamming distance: "+obj.distance(hash1, hash2));
    }
}
