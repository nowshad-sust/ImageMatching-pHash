/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.GSONHandler;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author nowshad
 */

public class JSONHandler {
    
     public static void toJSON(JSONObject obj, String outputDestination) {
         
        //outputDestination = ".\\resources\\dataset\\dataset.json";
	Gson gson = new Gson();
 
	// convert java object to JSON format,
	// and returned as JSON formatted string
	String json = gson.toJson(obj);
 
	try {
            File file = new File(outputDestination);
            if(!file.exists()){
                System.out.println("File doesn't exist");
    		file.createNewFile();
    		}
		//write converted json data to a file
		FileWriter writer = new FileWriter(outputDestination, true);
		writer.write(json);
		writer.close();
 
	} catch (IOException e) {
		e.printStackTrace();
	}
        
	System.out.println(json);
    }
    
     public static JSONObject fromJSON(String sourcePath) {
 
	Gson gson = new Gson();
        JSONObject obj = null;
	try {
 
		BufferedReader br = new BufferedReader(
			new FileReader(sourcePath));
                
		//convert the json string back to object
		obj = gson.fromJson(br, JSONObject.class);
 
		System.out.println(obj);
                
 
	} catch (IOException e) {
		e.printStackTrace();
	}
        return obj;
    }
     
}
