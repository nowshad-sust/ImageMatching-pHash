/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GlobalAccess;

import java.io.IOException;

/**
 *
 * @author nowshad
 */
public class Test {
    public static void main(String args[]) throws IOException{
        String path = "C:\\Users\\nowshad\\Desktop\\Taka\\Bangladesh.jpg";
        byte[] byteArray = ConvertImage.imageToByteArray(path);
        GlobalAccessToSearch obj = new GlobalAccessToSearch(byteArray, true);
        obj.preloadData();
        obj.search();
    }
}
