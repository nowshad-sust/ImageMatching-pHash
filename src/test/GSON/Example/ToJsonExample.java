/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.GSON.Example;
 
import test.GSON.Example.DataObject;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
 
public class ToJsonExample {
    public static void main(String[] args) {
 
	DataObject obj = new DataObject();
	Gson gson = new Gson();
 
	// convert java object to JSON format,
	// and returned as JSON formatted string
	String json = gson.toJson(obj);
 
	try {
		//write converted json data to a file named "file.json"
		FileWriter writer = new FileWriter("H:\\file.json");
		writer.write(json);
		writer.close();
 
	} catch (IOException e) {
		e.printStackTrace();
	}
 
	System.out.println(json);
 
    }
}
