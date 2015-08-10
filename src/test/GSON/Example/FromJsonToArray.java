/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.GSON.Example;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author nowshad
 */
public class FromJsonToArray {
    
    public List<DatasetObject> retrieveArrayfromJson(String sourcePath){
        
        
        Gson gson = new Gson();
 
	try {
 
		BufferedReader br = new BufferedReader(
			new FileReader(sourcePath));
 
		//convert the json string back to object
		ToJSONFromArray obj = gson.fromJson(br, ToJSONFromArray.class);
 
                return obj.getMainJsonArray();
 
	} catch (IOException e) {
		e.printStackTrace();
	}
        return null;
    }
    
}
