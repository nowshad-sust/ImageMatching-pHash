/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.GSON.Example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
 
public class FromJsonExample {
    public static void main(String[] args) {
 
	Gson gson = new Gson();
 
	try {
 
		BufferedReader br = new BufferedReader(
			new FileReader("H:\\file.json"));
 
		//convert the json string back to object
		DataObject obj = gson.fromJson(br, DataObject.class);
 
		System.out.println(obj.getData2());
 
	} catch (IOException e) {
		e.printStackTrace();
	}
 
    }
}