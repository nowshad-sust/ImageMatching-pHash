/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.GSON.Example;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nowshad
 */
public class ToJSONFromArray {
    private List<DatasetObject> mainJsonArray = new ArrayList<DatasetObject>();
    
    
    public void addObjectToArray(DatasetObject obj){
        mainJsonArray.add(obj);
        System.out.println("Object added. size = " + mainJsonArray.size());
    }
    
    public void writeJsonArray(String destinationFullPath){
        
        ToJSONFromArray mainArrayObject = this; 
        //printing the array first
        
        for(int i=0;i < mainJsonArray.size();i++){
            DatasetObject DObj = mainJsonArray.get(i);
            System.out.println("object no - "+i);
            System.out.println(DObj.getImagePHashString());
            System.out.println(DObj.getSourceImagePath());
            System.out.println(DObj.getValueInTaka());
        }
        
        
        Gson gson = new Gson();
 
	// convert java object to JSON format,
	// and returned as JSON formatted string
	String json = gson.toJson(mainArrayObject);
 
	try {
		//write converted json data to a file named "file.json"
		FileWriter writer = new FileWriter(destinationFullPath);
		writer.write(json);
		writer.close();
 
	} catch (IOException e) {
		e.printStackTrace();
	}
 
	System.out.println(json);
    }

    public List<DatasetObject> getMainJsonArray() {
        return mainJsonArray;
    }

    public void setMainJsonArray(List<DatasetObject> mainJsonArray) {
        this.mainJsonArray = mainJsonArray;
    }
}
