/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.GSON.Example;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author nowshad
 */
public class MainJSONObjectArray {
    public void makeObjectArray(){
        //1st object
        DatasetObject DO1 = new DatasetObject();
        DO1.setImagePHashString("1000000");
        DO1.setSourceImagePath("nai");
        DO1.setValueInTaka(100);
        //2nd object
        
        DatasetObject DO2 = new DatasetObject();
        DO2.setImagePHashString("2000000");
        DO2.setSourceImagePath("ase");
        DO2.setValueInTaka(200);
        
        
        ToJSONFromArray objRef = new ToJSONFromArray();
        objRef.addObjectToArray(DO1);
        objRef.addObjectToArray(DO2);
        
        objRef.writeJsonArray(".\\src\\resources\\dataset\\dataset.json");
        
    }
    
    public static void main(String args[]){
        
        MainJSONObjectArray main = new MainJSONObjectArray();
        main.makeObjectArray();
        
        
        
    }
    
}
