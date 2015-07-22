/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.GSONHandler;

/**
 *
 * @author nowshad
 */

public class JSONObject {
    private int id;
    private String sourcePath;
    private String hashString;
    private int noteValue;
    
    public JSONObject(){
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getHashString() {
        return hashString;
    }

    public void setHashString(String hashString) {
        this.hashString = hashString;
    }

    public int getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(int noteValue) {
        this.noteValue = noteValue;
    }
    @Override
	public String toString() {
	   return "DataObject [ID="+ id + ", Hash=" + hashString + ", Value="
		+ noteValue + ", Source Path="+ sourcePath +"]";
	}
    
}
